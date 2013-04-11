/**
 * Copyright (C) 2012-2013 eBusiness Information (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.athomas.androidkickstartr;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.generator.ApplicationGenerator;
import com.athomas.androidkickstartr.generator.Generator;
import com.athomas.androidkickstartr.generator.MainActivityGenerator;
import com.athomas.androidkickstartr.generator.RestClientGenerator;
import com.athomas.androidkickstartr.generator.SampleFragmentGenerator;
import com.athomas.androidkickstartr.generator.ViewPagerAdapterGenerator;
import com.athomas.androidkickstartr.util.FileHelper;
import com.athomas.androidkickstartr.util.GitHubber;
import com.athomas.androidkickstartr.util.LibraryHelper;
import com.athomas.androidkickstartr.util.RefHelper;
import com.athomas.androidkickstartr.util.ResourcesUtils;
import com.athomas.androidkickstartr.util.TemplatesFileHelper;
import com.athomas.androidkickstartr.util.Zipper;
import com.sun.codemodel.JCodeModel;

import freemarker.template.TemplateException;

public class Kickstartr {

	private final static Logger LOGGER = LoggerFactory.getLogger(Kickstartr.class);

	private AppDetails appDetails;
	private JCodeModel jCodeModel;
	private FileHelper fileHelper;

	public Kickstartr(AppDetails appDetails) {
		this.appDetails = appDetails;

		jCodeModel = new JCodeModel();
		fileHelper = new FileHelper(appDetails.getName(), appDetails.getPackageName(), appDetails.isMaven());

		extractResources(appDetails);
	}

	public File zipify() {
        createDirectory();
        File zipFile = null;
        try {
            File targetDir = fileHelper.getTargetDir();
            zipFile = new File(targetDir, appDetails.getName() + "-AndroidKickstartr.zip");
            Zipper.zip(fileHelper.getFinalDir(), zipFile);
            LOGGER.debug("application sources zipped");
        } catch (IOException e) {
            LOGGER.error("a problem occured during the compression", e);
            return null;
        }

        LOGGER.debug("AndroidKickstartR generation done");
        return zipFile;
    }
	
    public Repository githubify(String accessToken) throws IOException, GitAPIException {
        LOGGER.debug("Github creation started");
        createDirectory();
        GitHubber gitHubber = new GitHubber(accessToken);
        Repository repository = gitHubber.createCommit(fileHelper.getFinalDir(), fileHelper.getProjectDir().getName());
        return repository;
    }

    private void extractResources(AppDetails appDetails) {
        try {
            File resourcesDir = fileHelper.getKickstartrResourcesDir();
            if (resourcesDir.exists() || resourcesDir.list() == null || resourcesDir.list().length == 0) {
                ResourcesUtils.copyResourcesTo(resourcesDir, "org.eclipse.jdt.apt.core.prefs");
            }
        } catch (IOException e) {
            LOGGER.error("an error occured during the resources extraction", e);
        }
    }

    private void createDirectory() {
        LOGGER.info("generation of " + appDetails + " : " + appDetails);

        if (appDetails.isRestTemplate() || appDetails.isAcra()) {
            List<String> permissions = appDetails.getPermissions();
            permissions.add("android.permission.INTERNET");
        }
		try {
			copyResDir();
			LOGGER.debug("res dir copied.");
		} catch (IOException e) {
			LOGGER.error("problem occurs during the resources copying", e);
		}

        try {
            generateSourceCode();
            LOGGER.debug("source code generated from templates.");
        } catch (IOException e) {
            LOGGER.error("generated code file not generated", e);
        }

        try {
            File androidResDir = fileHelper.getTargetAndroidResDir();
            File sourceResDir = fileHelper.getResDir();
            FileUtils.copyDirectory(sourceResDir, androidResDir);
            LOGGER.debug("res dir copied.");
        } catch (IOException e) {
            LOGGER.error("problem occurs during the resources copying", e);
        }

        if (appDetails.isMaven()) {
            // create src/text/java - it avoids an error when import to Eclipse
            File targetTestDir = fileHelper.getTargetTestDir();
            File removeMe = new File(targetTestDir, "REMOVE.ME");
            try {
                removeMe.createNewFile();
            } catch (IOException e) {
                LOGGER.error("an error occured during the REMOVE.ME file creation", e);
            }
        }

        try {
            TemplatesFileHelper templatesFileHelper = new TemplatesFileHelper(appDetails, fileHelper);
            templatesFileHelper.generate();
            LOGGER.debug("files generated from templates.");
        } catch (IOException e) {
            LOGGER.error("problem during ftl files loading", e);
        } catch (TemplateException e) {
            LOGGER.error("problem during template processing", e);
        }

        try {
            if (appDetails.isEclipse()) {
                if (appDetails.isAndroidAnnotations()) {
                    File targetEclipseJdtAptCorePrefsFile = fileHelper.getTargetEclipseJdtAptCorePrefsFile();
                    File eclipseJdtAptCorePrefs = fileHelper.getEclipseJdtAptCorePrefs();
                    FileUtils.copyFile(eclipseJdtAptCorePrefs, targetEclipseJdtAptCorePrefsFile);
                    LOGGER.debug("org.eclipse.jdt.apt.core.prefs copied");
                }
                File targetEclipseJdtCorePrefsFile = fileHelper.getTargetEclipseJdtCorePrefsFile();
                File eclipseJdtCorePrefs = fileHelper.getEclipseJdtCorePrefs();
                FileUtils.copyFile(eclipseJdtCorePrefs, targetEclipseJdtCorePrefsFile);
                LOGGER.debug("org.eclipse.jdt.core.prefs copied");
            }
        } catch (IOException e) {
            LOGGER.error("a problem occured during the org.eclipse.jdt.apt.core.prefs copying", e);
        }

        LibraryHelper libraryManager = new LibraryHelper(appDetails, fileHelper);
        libraryManager.go();
        LOGGER.debug("libraries copied");
    }

    private void generateSourceCode() throws IOException {
		List<Generator> generators = new ArrayList<Generator>();

		generators.add(new MainActivityGenerator(appDetails));

		if (appDetails.isViewPager()) {
			generators.add(new ViewPagerAdapterGenerator(appDetails));
			generators.add(new SampleFragmentGenerator(appDetails));
		}

		if (appDetails.isRestTemplate() && appDetails.isAndroidAnnotations()) {
			generators.add(new RestClientGenerator(appDetails));
		}

		if (appDetails.isAcra()) {
			generators.add(new ApplicationGenerator(appDetails));
		}

		RefHelper refHelper = new RefHelper(jCodeModel);
		refHelper.r(appDetails.getR());

		for (Generator generator : generators) {
			generator.generate(jCodeModel, refHelper);
		}
		jCodeModel.build(fileHelper.getTargetSourceDir());
	}
	
	private void copyResDir() throws IOException {
		File androidResDir = fileHelper.getTargetAndroidResDir();
		File sourceResDir = fileHelper.getResDir();
		
		FileFilter filter = null;
		List<IOFileFilter> fileFilters = new ArrayList<IOFileFilter>();
		
		if (!appDetails.isListNavigation() && !appDetails.isTabNavigation()) {
			// Exclude arrays.xml from the copy
			IOFileFilter resArraysFilter = FileFilterUtils.nameFileFilter("arrays.xml");
		    IOFileFilter fileFilter = FileFilterUtils.notFileFilter(resArraysFilter);
		    fileFilters.add(fileFilter);
		}
		
		if (!appDetails.isViewPager()) {
			// Exclude fragment_sample.xml from the copy
			IOFileFilter resFragmentSampleFilter = FileFilterUtils.nameFileFilter("fragment_sample.xml");
		    IOFileFilter fileFilter = FileFilterUtils.notFileFilter(resFragmentSampleFilter);
		    fileFilters.add(fileFilter);
		}
		
		if (!fileFilters.isEmpty()) {
			filter = FileFilterUtils.and(fileFilters.toArray(new IOFileFilter[fileFilters.size()]));
		}
		
		FileUtils.copyDirectory(sourceResDir, androidResDir, filter);
	}

	public void clean() {
		File targetDir = fileHelper.getTargetDir();
		try {
			FileUtils.cleanDirectory(targetDir);
		} catch (IOException e) {
			LOGGER.error("a problem occured during target dir cleaning", e);
		}
	}
}

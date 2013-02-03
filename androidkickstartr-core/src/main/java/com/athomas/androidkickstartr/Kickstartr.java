package com.athomas.androidkickstartr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.generator.ApplicationGenerator;
import com.athomas.androidkickstartr.generator.Generator;
import com.athomas.androidkickstartr.generator.MainActivityGenerator;
import com.athomas.androidkickstartr.generator.RestClientGenerator;
import com.athomas.androidkickstartr.generator.SampleFragmentGenerator;
import com.athomas.androidkickstartr.generator.ViewPagerAdapterGenerator;
import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.FileHelper;
import com.athomas.androidkickstartr.util.LibraryHelper;
import com.athomas.androidkickstartr.util.RefHelper;
import com.athomas.androidkickstartr.util.ResourcesUtils;
import com.athomas.androidkickstartr.util.TemplatesFileHelper;
import com.athomas.androidkickstartr.util.Zipper;
import com.sun.codemodel.JCodeModel;

import freemarker.template.TemplateException;

public class Kickstartr {

	private final static Logger LOGGER = LoggerFactory.getLogger(Kickstartr.class);

	private State state;
	private Application application;
	private JCodeModel jCodeModel;
	private FileHelper fileHelper;

	public Kickstartr(State state, Application application) {
		this.state = state;
		this.application = application;

		jCodeModel = new JCodeModel();
		fileHelper = new FileHelper(application.getName(), application.getPackageName(), state.isMaven());

		extractResources(state, application);
	}

	private void extractResources(State state, Application application) {
		try {
			File resourcesDir = fileHelper.getKickstartrResourcesDir();
			if (resourcesDir.exists() || resourcesDir.list() == null || resourcesDir.list().length == 0) {
				ResourcesUtils.copyResourcesTo(resourcesDir, "org.eclipse.jdt.apt.core.prefs");
			}
		} catch (IOException e) {
			LOGGER.error("an error occured during the resources extraction", e);
		}
	}

	public File start() {
		LOGGER.info("generation of " + application + " : " + state);

		if (state.isRestTemplate()) {
			List<String> permissions = application.getPermissions();
			permissions.add("android.permission.INTERNET");
		}

		try {
			generateSourceCode();
			LOGGER.debug("source code generated from templates.");
		} catch (IOException e) {
			LOGGER.error("generated code file not generated", e);
			return null;
		}

		try {
			File androidResDir = fileHelper.getTargetAndroidResDir();
			File sourceResDir = fileHelper.getResDir();
			FileUtils.copyDirectory(sourceResDir, androidResDir);
			LOGGER.debug("res dir copied.");
		} catch (IOException e) {
			LOGGER.error("problem occurs during the resources copying", e);
			return null;
		}

		if (state.isMaven()) {
			// create src/text/java - it avoids an error when import to Eclipse
			File targetTestDir = fileHelper.getTargetTestDir();
			File removeMe = new File(targetTestDir, "REMOVEME");
			try {
				removeMe.createNewFile();
			} catch (IOException e) {
				LOGGER.error("an error occured during the REMOVEME file creation", e);
			}
		}

		try {
			TemplatesFileHelper templatesFileHelper = new TemplatesFileHelper(application, state, fileHelper);
			templatesFileHelper.generate();
			LOGGER.debug("files generated from templates.");
		} catch (IOException e) {
			LOGGER.error("problem during ftl files loading", e);
			return null;
		} catch (TemplateException e) {
			LOGGER.error("problem during template processing", e);
			return null;
		}

		try {
			if (state.isEclipse()) {
				if (state.isAndroidAnnotations()) {
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
			return null;
		}

		LibraryHelper libraryManager = new LibraryHelper(state, fileHelper);
		libraryManager.go();
		LOGGER.debug("libraries copied");

		File zipFile = null;
		try {
			File targetDir = fileHelper.getTargetDir();
			zipFile = new File(targetDir, application.getName() + "-AndroidKickstartr.zip");
			Zipper.zip(fileHelper.getFinalDir(), zipFile);
			LOGGER.debug("application sources zipped");
		} catch (IOException e) {
			LOGGER.error("a problem occured during the compression", e);
			return null;
		}

		LOGGER.debug("AndroidKickstartR generation done");
		return zipFile;
	}

	private void generateSourceCode() throws IOException {
		List<Generator> generators = new ArrayList<Generator>();

		generators.add(new MainActivityGenerator(state, application));

		if (state.isViewPager()) {
			generators.add(new ViewPagerAdapterGenerator(state, application));
			generators.add(new SampleFragmentGenerator(state, application));
		}

		if (state.isRestTemplate() && state.isAndroidAnnotations()) {
			generators.add(new RestClientGenerator(application));
		}

		if (state.isAcra()) {
			generators.add(new ApplicationGenerator(application));
		}

		RefHelper refHelper = new RefHelper(jCodeModel);
		refHelper.r(application.getR());

		for (Generator generator : generators) {
			generator.generate(jCodeModel, refHelper);
		}
		jCodeModel.build(fileHelper.getTargetSourceDir());
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

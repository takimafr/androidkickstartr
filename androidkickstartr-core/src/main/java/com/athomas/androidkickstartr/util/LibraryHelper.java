package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.AppDetails;

public class LibraryHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(LibraryHelper.class);

	private AppDetails state;
	private FileHelper fileHelper;

	public LibraryHelper(AppDetails state, FileHelper fileHelper) {
		this.state = state;
		this.fileHelper = fileHelper;
	}

	public void go() {
		if (state.isActionBarSherlock()) {
			copyLibraryToProject("ActionBarSherlock/actionbarsherlock");
		}

		if (state.isViewPagerIndicator()) {
			copyLibraryToProject("ViewPagerIndicator");
		}

		if (!state.isMaven()) {
			if (state.isAndroidAnnotations()) {
				copyToLibs("androidannotations-api-2.7.jar");
				copyToCompileLibs("androidannotations-2.7.jar");
			}

			if (state.isNineOldAndroids()) {
				copyToLibs("nineoldandroids-2.4.0.jar");
			}

			if (state.isRestTemplate()) {
				copyToLibs("spring-android-core-1.0.1.RELEASE.jar");
				copyToLibs("spring-android-rest-template-1.0.1.RELEASE.jar");
			}

			if (state.isSupportV4()) {
				copyToLibs("android-support-v4.jar");
			}

			if (state.isAcra()) {
				copyToLibs("acra-4.4.0.jar");
			}

			if (state.isRoboguice()) {
				copyToLibs("roboguice-2.0.jar");
				copyToLibs("guice-3.0-no_aop.jar");
				//Required since Guice 3.0
				copyToLibs("javax.inject-1.jar");
			}
		}

	}

	private void copyLibraryToProject(String lib) {
		try {
			File library = fileHelper.getLibraryFile(lib);
			File projectDir = fileHelper.getFinalDir();
			FileUtils.copyDirectoryToDirectory(library, projectDir);
		} catch (IOException e) {
			LOGGER.error("a problem occured during the copy of the library " + lib, e);
		}
	}

	private void copyToLibs(String jar) {
		try {
			File library = fileHelper.getLibraryFile(jar);
			File libsDir = fileHelper.getTargetLibsDir();
			FileUtils.copyFileToDirectory(library, libsDir);
		} catch (IOException e) {
			LOGGER.error("a problem occured during the copy of " + jar + " libs", e);
		}
	}

	private void copyToCompileLibs(String jar) {
		try {
			File library = fileHelper.getLibraryFile(jar);
			File extCompileDir = fileHelper.getTargetExtCompileDir();
			FileUtils.copyFileToDirectory(library, extCompileDir);
		} catch (IOException e) {
			LOGGER.error("a problem occured during the copy of " + jar + " to compile-libs", e);
		}
	}

}

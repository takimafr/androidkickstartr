package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.State;

public class LibraryHelper {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LibraryHelper.class);

	private State state;
	private FileHelper fileHelper;

	public LibraryHelper(State state, FileHelper fileHelper) {
		this.state = state;
		this.fileHelper = fileHelper;
	}

	public void go() {
		if (state.isActionBarSherlock()) {
			copyLibraryToProject("ActionBarSherlock/library");
		}

		if (state.isViewPagerIndicator()) {
			copyLibraryToProject("ViewPagerIndicator");
		}

		if (!state.isMaven()) {
			if (state.isAndroidAnnotations()) {
				copyToLibs("androidannotations-2.6-api.jar");
				copyToExtLibs("androidannotations-2.6.jar");
			}

			if (state.isNineOldAndroids()) {
				copyToLibs("nineoldandroids-2.4.0.jar");
			}

			if (state.isRestTemplate()) {
				copyToLibs("spring-android-core-1.0.0.RELEASE.jar");
				copyToLibs("spring-android-rest-template-1.0.0.RELEASE.jar");
			}

			if (state.isSupportV4()) {
				copyToLibs("android-support-v4.jar");
			}

			if (state.isAcra()) {
				copyToLibs("acra-4.3.0.jar");
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

	private void copyToExtLibs(String jar) {
		try {
			File library = fileHelper.getLibraryFile(jar);
			File extLibsDir = fileHelper.getTargetExtLibsDir();
			FileUtils.copyFileToDirectory(library, extLibsDir);
		} catch (IOException e) {
			LOGGER.error("a problem occured during the copy of " + jar + " to ext-libs", e);
		}
	}

}

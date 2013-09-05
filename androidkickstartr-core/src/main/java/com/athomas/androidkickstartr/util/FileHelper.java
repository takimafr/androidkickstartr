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
package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileHelper {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileHelper.class);

	private String applicationName;
	private String packageName;
	private boolean maven;
	private File resourcesTempDir;
	private File targetTempDir;

	public FileHelper(String applicationName, String packageName, boolean maven, String targetDirName) {
		this.applicationName = applicationName;
		this.packageName = packageName;
		this.maven = maven;

		if (targetDirName != null) {
			targetTempDir = new File(targetDirName);
			targetTempDir.mkdir();
			return;
		}

		// create default temporary target folder
		try {
			targetTempDir = File.createTempFile("androidkickstartr-tmp", null);
			LOGGER.debug("create tempory target dir [" + targetTempDir + "]");
		} catch (IOException e) {
			LOGGER.error("error during temporary target dir creation", e);
		}
		targetTempDir.delete();
		targetTempDir.mkdir();
	}

	public File getTargetDir() {
		return targetTempDir;
	}

	public File getKickstartrResourcesDir() throws IOException {
		if (resourcesTempDir == null || !resourcesTempDir.exists()) {
			resourcesTempDir = File.createTempFile("AndroidKickstartR-Resources", null);
			resourcesTempDir.delete();
			resourcesTempDir.mkdir();
		}
		return resourcesTempDir;
	}

	public String getTargetSourcePath() {
		String srcPath = maven ? "/src/main/java" : "/src";
		return getProjectPath() + srcPath;
	}

	public File getTargetSourceDir() {
		File dir = new File(getTargetSourcePath());
		dir.mkdirs();
		return dir;
	}

	public String getTargetTestPath() {
		return getProjectPath() + "/src/test/java";
	}

	public File getTargetTestDir() {
		File dir = new File(getTargetTestPath());
		dir.mkdirs();
		return dir;
	}

	public String getFinalDirName() {
		return applicationName + "-AndroidKickstartr";
	}

	private String getFinalPath() {
		return targetTempDir.getPath() + "/" + getFinalDirName();
	}

	private String getProjectPath() {
		return getFinalPath() + "/" + applicationName;
	}

	public File getTemplatesDir() throws IOException {
		return getResource("templates/");
	}

	private File getDir(String path) {
		File dir = new File(path);
		dir.mkdirs();
		return dir;
	}

	public File getResDir() throws IOException {
		return getResource("res/");
	}

	public File getTargetAndroidResDir() throws IOException {
		return getDir(getProjectPath() + "/res");
	}

	public File getTargetProjectDir() {
		return getDir(getProjectPath());
	}

	public File getProjectDir() {
		return getDir(getProjectPath());
	}

	public File getFinalDir() {
		return getDir(getFinalPath());
	}

    public File getTargetDir(String dir) {
       return getDir(getProjectPath() + dir);
    }

	public File getTargetAndroidManifestFile() throws IOException {
		return createFile(getProjectPath() + "/AndroidManifest.xml");
	}

	private File createFile(String path) throws IOException {
		File file = new File(path);

		File parent = file.getParentFile();
		if (!parent.exists() && !parent.mkdirs()) {
			throw new IllegalStateException("Couldn't create dir: " + parent);
		}

		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public File getTargetPomFile() throws IOException {
		return createFile(getProjectPath() + "/pom.xml");
	}

	public File getTargetFactoryPathFile() throws IOException {
		return createFile(getProjectPath() + "/.factorypath");
	}

	public File getTargetStylesFile() throws IOException {
		return createFile(getProjectPath() + "/res/values/styles.xml");
	}

	public File getTargetActivityMainFile() throws IOException {
		return createFile(getProjectPath() + "/res/layout/activity_main.xml");
	}

	public File getTargetProjectFile() throws IOException {
		return createFile(getProjectPath() + "/.project");
	}

	public File getTargetProguardFile() throws IOException {
		return createFile(getProjectPath() + "/proguard.cfg");
	}

	public File getTargetClasspathFile() throws IOException {
		return createFile(getProjectPath() + "/.classpath");
	}

	public File getTargetStringsFile() throws IOException {
		return createFile(getProjectPath() + "/res/values/strings.xml");
	}

	public File getTargetEclipseJdtAptCorePrefsFile() throws IOException {
		return createFile(getProjectPath() + "/.settings/org.eclipse.jdt.apt.core.prefs");
	}

	public File getTargetEclipseJdtCorePrefsFile() throws IOException {
		return createFile(getProjectPath() + "/.settings/org.eclipse.jdt.core.prefs");
	}

	public File getTargetProjectPropertiesFile() throws IOException {
		return createFile(getProjectPath() + "/project.properties");
	}

	public File getTargetReadmeFile() throws IOException {
		return createFile(getFinalPath() + "/README.md");
	}

	public File getTargetRoboSherlockActivityFile() throws IOException {
		return createFile(getTargetSourcePath() + "/" + packageName.replace(".", "/") + "/robosherlock/RoboSherlockActivity.java");
	}

	public File getTargetRoboSherlockFragmentFile() throws IOException {
		return createFile(getTargetSourcePath() + "/" + packageName.replace(".", "/") + "/robosherlock/RoboSherlockFragment.java");
	}

	public File getTargetRoboSherlockFragmentActivityFile() throws IOException {
		return createFile(getTargetSourcePath() + "/" + packageName.replace(".", "/") + "/robosherlock/RoboSherlockFragmentActivity.java");
	}

	public File getTargetRobolectricMockActionBarFile() throws IOException {
		return createFile(getTargetTestPath() + "/" + packageName.replace(".", "/") + "/absmock/MockActionBar.java");
	}

	public File getTargetRobolectricMockActionBarSherlockFile() throws IOException {
		return createFile(getTargetTestPath() + "/" + packageName.replace(".", "/") + "/absmock/MockActionBarSherlock.java");
	}

	public File getTargetRobolectricMockSherlockMenuInflaterFile() throws IOException {
		return createFile(getTargetTestPath() + "/" + packageName.replace(".", "/") + "/absmock/MockSherlockMenuInflater.java");
	}

	public File getTargetRobolectricMockTabFile() throws IOException {
		return createFile(getTargetTestPath() + "/" + packageName.replace(".", "/") + "/absmock/MockTab.java");
	}

	public File getTargetRobolectricCustomTestRunnerFile() throws IOException {
		return createFile(getTargetTestPath() + "/" + packageName.replace(".", "/") + "/absmock/CustomTestRunner.java");
	}

	public File getEclipseJdtAptCorePrefs() throws IOException {
		return getResource("org.eclipse.jdt.apt.core.prefs");
	}

	public File getResource(String filename) throws IOException {
		return getFile(getKickstartrResourcesDir(), filename);
	}

	private File getFile(File parent, String path) throws IOException {
		File file = new File(parent, path);
		if (!file.exists()) {
			throw new FileNotFoundException(path + " doesn't exist");
		}
		return file;
	}

	public File getEclipseJdtCorePrefs() throws IOException {
		return getResource("org.eclipse.jdt.core.prefs");
	}

	public File getProjectPropertiesFile() throws IOException {
		return getResource("project.properties");
	}

	public File getLibraryFile(String filename) throws IOException {
		return getResource("libs/" + filename);
	}

}

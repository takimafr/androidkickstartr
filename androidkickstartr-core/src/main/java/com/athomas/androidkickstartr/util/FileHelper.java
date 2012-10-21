package com.athomas.androidkickstartr.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHelper {

	private static final String TARGET = "generated";
	private String applicationName;
	private boolean maven;
	private String resourcePath;

	public FileHelper(String applicationName, String resourcesPath, boolean maven) {
		this.applicationName = applicationName;
		this.resourcePath = resourcesPath;
		this.maven = maven;
	}

	public File getTargetSourceDir() {
		String srcPath = maven ? "/src/main/java" : "/src";

		File dir = new File(getProject() + srcPath);
		dir.mkdirs();
		return dir;
	}

	public File getTargetTestDir() {
		File dir = new File(getProject() + "/src/test/java");
		dir.mkdirs();
		return dir;
	}

	private String getFinalPath() {
		return TARGET + "/" + applicationName + "-AndroidKickstartr";
	}

	private String getProject() {
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
		return getDir(getProject() + "/res");
	}

	public File getTargetProjectDir() {
		return getDir(getProject());
	}

	public File getFinalDir() {
		return getDir(getFinalPath());
	}

	public File getTargetDir() {
		return getDir(TARGET);
	}

	public File getTargetLibsDir() throws IOException {
		return getDir(getProject() + "/libs");
	}

	public File getTargetExtLibsDir() throws IOException {
		return getDir(getProject() + "/ext-libs");
	}

	public File getTargetAndroidManifestFile() throws IOException {
		return createFile(getProject() + "/AndroidManifest.xml");
	}

	private File createFile(String path) throws IOException {
		File file = new File(path);

		File parent = file.getParentFile();
		if(!parent.exists() && !parent.mkdirs()){
		    throw new IllegalStateException("Couldn't create dir: " + parent);
		}
		
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	private File getFile(String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException(path + " doesn't exist");
		}
		return file;
	}

	public File getTargetPomFile() throws IOException {
		return createFile(getProject() + "/pom.xml");
	}

	public File getTargetFactoryPathFile() throws IOException {
		return createFile(getProject() + "/.factorypath");
	}

	public File getTargetStylesFile() throws IOException {
		return createFile(getProject() + "/res/values/styles.xml");
	}

	public File getTargetProjectFile() throws IOException {
		return createFile(getProject() + "/.project");
	}

	public File getTargetClasspathFile() throws IOException {
		return createFile(getProject() + "/.classpath");
	}

	public File getTargetStringsFile() throws IOException {
		return createFile(getProject() + "/res/values/strings.xml");
	}

	public File getTargetEclipseJdtAptCorePrefsFile() throws IOException {
		return createFile(getProject() + "/.settings/org.eclipse.jdt.apt.core.prefs");
	}

	public File getTargetEclipseJdtCorePrefsFile() throws IOException {
		return createFile(getProject() + "/.settings/org.eclipse.jdt.core.prefs");
	}

	public File getEclipseJdtAptCorePrefs() throws IOException {
		return getResource("org.eclipse.jdt.apt.core.prefs");
	}
	
	public File getEclipseJdtCorePrefs() throws IOException {
		return getResource("org.eclipse.jdt.core.prefs");
	}

	public File getProjectPropertiesFile() throws IOException {
		return getResource("project.properties");
	}

	public File getTargetProjectPropertiesFile() throws IOException {
		return createFile(getProject() + "/project.properties");
	}

	public File getLibraryFile(String filename) throws IOException {
		return getResource("libs/" + filename);
	}

	public File getResource(String filename) throws IOException {
		return getFile(resourcePath + filename);
	}

}

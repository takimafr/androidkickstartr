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
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;

/**
 * Useful utils for extracting resources from a jar
 * 
 * @author Alexandre THOMAS
 */
public class ResourcesUtils {

	public static void copyResourcesTo(File resourcesDir, String filepathMarker) throws IOException {

		if (resourcesDir == null) {
			throw new IllegalArgumentException("The resources dir must not be null");
		}

		ClassLoader classLoader = ResourcesUtils.class.getClassLoader();
		URL url = classLoader.getResource(filepathMarker);
		String protocol = url.getProtocol();

		if (protocol.equals("file")) {
			File src = new File("src/main/resources");
			FileUtils.copyDirectory(src, resourcesDir);
		} else if (protocol.equals("jar")) {
			copyResourcesToFromJar(resourcesDir, url);
		}
	}

	private static void copyResourcesToFromJar(File target, URL url) throws IOException {
		JarURLConnection connection = (JarURLConnection) url.openConnection();
		JarFile jarFile = connection.getJarFile();

		Enumeration<JarEntry> entries = jarFile.entries();

		while (entries.hasMoreElements()) {
			JarEntry jarEntry = entries.nextElement();
			InputStream is = jarFile.getInputStream(jarEntry);
			String entryPath = jarEntry.getName();

			File file = null;
			String dirs = "";
			if (entryPath.contains("/")) {
				int lastIndexOf = entryPath.lastIndexOf("/");
				dirs = (String) entryPath.subSequence(0, lastIndexOf + 1);
			}

			File parent = new File(target, dirs);
			parent.mkdirs();

			if (!jarEntry.isDirectory()) {
				String[] splitedPath = entryPath.split("/");
				String fileName = splitedPath[splitedPath.length - 1];
				file = new File(parent, fileName);
				FileUtils.copyInputStreamToFile(is, file);
			}
		}
	}
}
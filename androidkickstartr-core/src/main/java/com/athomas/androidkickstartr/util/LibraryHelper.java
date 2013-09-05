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

import com.athomas.androidkickstartr.AppDetails;
import com.athomas.androidkickstartr.model.Jar;
import com.athomas.androidkickstartr.model.Library;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(LibraryHelper.class);

    private AppDetails state;
    private FileHelper fileHelper;

    public LibraryHelper(AppDetails state, FileHelper fileHelper) {
        this.state = state;
        this.fileHelper = fileHelper;
    }

    public void go() {
        List<Library> libraries = state.getLibraries();
        if (libraries == null || libraries.isEmpty()) return;

        copyApkLibFiles(libraries);
        copyLibsJars(libraries);
    }

    private void copyApkLibFiles(List<Library> libraries) {
        // add apklib
        if (state.isEclipse() || !state.isMaven()) {
            for (Library lib : libraries) {
                if (lib.isApklib()) {
                    String apkLibFilename = lib.getApkLibFilename();
                    copyLibraryToProject(apkLibFilename);
                }
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

    private void copyLibsJars(List<Library> libraries) {
        // add jars
        if (!state.isMaven()) {
            for (Library lib : libraries) {
                List<Jar> jars = lib.getJars();
                for (Jar jar : jars) {
                    String jarName = jar.getName();
                    String destinationDir = jar.getDestinationDir();
                    copyLibTo(jarName, destinationDir);
                }
            }
        }
    }

    private void copyLibTo(String jar, String destName) {
        try {
            File library = fileHelper.getLibraryFile(jar);
            File libsDir = fileHelper.getTargetDir(destName);
            FileUtils.copyFileToDirectory(library, libsDir);
        } catch (IOException e) {
            LOGGER.error("a problem occured during the copy of " + jar + " libs", e);
        }
    }

}

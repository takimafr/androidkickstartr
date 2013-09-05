package com.athomas.androidkickstartr.model;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String version;
    private String name;
    private ArrayList<MavenDep> mavenDeps;
    private List<Jar> jars;
    private boolean apklib;
    private String apkLibFilename;

    private Library() {
    }

    public static Builder build() {
        return new Builder();
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public List<MavenDep> getMavenDeps() {
        return mavenDeps;
    }

    public List<Jar> getJars() {
        return jars;
    }

    public boolean isApklib() {
        return apklib;
    }

    public String getApkLibFilename() {
        return apkLibFilename;
    }

    public static class Builder {
        private Library instance;

        public Builder() {
            this.instance = new Library();
        }

        public Builder version(String version) {
            instance.version = version;
            return this;
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder maven(MavenDep conf) {
            if (instance.mavenDeps == null) {
                instance.mavenDeps = new ArrayList<MavenDep>();
            }
            instance.mavenDeps.add(conf);
            return this;
        }

        public Builder jar(Jar jar) {
            if (instance.jars == null) {
                instance.jars = new ArrayList<Jar>();
            }
            instance.jars.add(jar);
            return this;
        }

        public Builder apklib(boolean apklib) {
            instance.apklib = apklib;
            return this;
        }

        public Builder apklibFilename(String apklibFilename) {
            instance.apkLibFilename = apklibFilename;
            return this;
        }

        public Library build() {
            return instance;
        }

    }
}

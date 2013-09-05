package com.athomas.androidkickstartr.model;

public class Jar {
    
    private String name;
    private String destinationDir = "libs";

    public String getName() {
        return name;
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public static Builder jar() {
        return new Builder();
    }

    public static class Builder {
        private Jar instance;

        public Builder() {
            this.instance = new Jar();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder destinationDir(String dstDir) {
            instance.destinationDir = dstDir;
            return this;
        }

        public Jar get() {
            return instance;
        }
    }

}

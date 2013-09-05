package com.athomas.androidkickstartr.model;

public class MavenDep {

    private String groupId;
    private String artifactId;
    private String type;
    private String scope;

    private MavenDep() {}

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getType() {
        return type;
    }

    public String getScope() {
        return scope;
    }

    public static Builder dep() {
        return new Builder();
    }

    public static class Builder {
        private MavenDep instance;

        public Builder() {
            this.instance = new MavenDep();
        }

        public Builder groupId(String groupId) {
            instance.groupId = groupId;
            return this;
        }

        public Builder artifactId(String artifactId) {
            instance.artifactId = artifactId;
            return this;
        }

        public Builder type(String type) {
            instance.type = type;
            return this;
        }

        public Builder scope(String scope) {
            instance.scope = scope;
            return this;
        }

        public MavenDep get() {
            return instance;
        }
    }
}

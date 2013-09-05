package com.athomas.androidkickstartr.model;

public final class Libraries {

    private Libraries() {

    }

    public static Library androidAnnotations() {
        return Library.build().
                version("2.7.1").
                name("androidannotations").
                jar(Jar.jar().
                        name("androidannotations-api-2.7.1.jar").
                        get()).
                jar(Jar.jar().
                        name("androidannotations-2.7.1.jar").
                        destinationDir("compile-libs").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.googlecode.androidannotations").
                        artifactId("androidannotations-api").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.googlecode.androidannotations").
                        artifactId("androidannotations").
                        scope("provided").
                        get()).
                build();
    }

    public static Library viewPagerIndicator() {
        return Library.build().
                version("2.4.0").
                name("viewpagerindicator").
                apklib(true).
                apklibFilename("ViewPagerIndicator").
                maven(MavenDep.dep().
                        groupId("com.viewpagerindicator").
                        artifactId("library").
                        type("apklib").
                        get()).
                build();
    }

    public static Library restTemplate() {
        return Library.build().
                version("1.0.1.RELEASE").
                name("resttemplate").
                jar(Jar.jar().
                        name("spring-android-core-1.0.1.RELEASE.jar").
                        get()).
                jar(Jar.jar().
                        name("spring-android-rest-template-1.0.1.RELEASE.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("org.springframework.android").
                        artifactId("spring-android-rest-template").
                        get()).
                build();
    }

    public static Library actionBarSherlock() {
        return Library.build().
                version("4.3.1").
                name("actionbarsherlock").
                apklib(true).
                apklibFilename("ActionBarSherlock/actionbarsherlock").
                maven(MavenDep.dep().
                        groupId("com.actionbarsherlock").
                        artifactId("actionbarsherlock").
                        type("jar").
                        scope("provided").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.actionbarsherlock").
                        artifactId("actionbarsherlock").
                        type("apklib").
                        get()).
                build();
    }

    public static Library nineOldAndroids() {
        return Library.build().
                version("2.4.0").
                name("nineoldandroids").
                jar(Jar.jar().
                        name("nineoldandroids-2.4.0.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.nineoldandroids").
                        artifactId("library").
                        get()).
                build();
    }

    public static Library supportV4() {
        return Library.build().
                version("r7").
                name("support-v4").
                maven(MavenDep.dep().
                        groupId("com.google.android").
                        artifactId("support-v4").
                        get()).
                build();
    }

    public static Library roboguice() {
        return Library.build().
                version("2.0").
                name("roboguice").
                jar(Jar.jar().
                        name("roboguice-2.0.jar").
                        get()).
                jar(Jar.jar().
                        name("guice-3.0-no_aop.jar").
                        get()).
                jar(Jar.jar().
                        name("javax.inject-1.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("org.roboguice").
                        artifactId("roboguice").
                        get()).
                build();
    }

    public static Library robolectric() {
        return Library.build().
                version("2.1.1").
                name("robolectric").
                maven(MavenDep.dep().
                        groupId("org.robolectric").
                        artifactId("robolectric").
                        scope("test").
                        get()).
                build();
    }

    public static Library junit() {
        return Library.build().
                version("4.11").
                name("junit").
                maven(MavenDep.dep().
                        groupId("junit").
                        artifactId("junit").
                        scope("test").
                        get()).
                build();
    }

    public static Library acra() {
        return Library.build().
                version("4.4.0").
                name("acra").
                jar(Jar.jar().
                        name("acra-4.4.0.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("ch.acra").
                        artifactId("acra").
                        get()).
                build();
    }

    public static Library eventbus() {
        return Library.build().
                version("2.0.2").
                name("eventbus").
                jar(Jar.jar().
                        name("eventbus-2.0.2.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("de.greenrobot").
                        artifactId("eventbus").
                        get()).
                build();
    }

    public static Library iconify() {
        return Library.build().
                version("1.0.1").
                name("iconify").
                jar(Jar.jar().
                        name("android-iconify-1.0.1.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.joanzapata.android").
                        artifactId("android-iconify").
                        get()).
                build();
    }

    public static Library robospice() {
        return Library.build().
                version("1.4.6").
                name("robospice").
                jar(Jar.jar().
                        name("robospice-1.4.6.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.octo.android.robospice").
                        artifactId("robospice").
                        get()).
                build();
    }

    public static Library retrofit() {
        return Library.build().
                version("1.2.1").
                name("retrofit").
                jar(Jar.jar().
                        name("retrofit-1.2.1.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.squareup.retrofit").
                        artifactId("retrofit").
                        get()).
                build();
    }
    public static Library okhttp() {
        return Library.build().
                version("1.2.1").
                name("okhttp").
                jar(Jar.jar().
                        name("okhttp-1.2.1.jar").
                        get()).
                maven(MavenDep.dep().
                        groupId("com.squareup.okhttp").
                        artifactId("okhttp").
                        get()).
                build();
    }
}

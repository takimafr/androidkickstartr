-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-ignorewarnings
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature
 

-keepattributes *Annotation*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.content.Context {
    public void *(android.view.View);
    public void *(android.view.MenuItem);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

<#if application.supportV4>
# -- Support v4 --

-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
</#if>

<#if application.actionBarSherlock>
# -- Action Bar Sherlock --
# from http://actionbarsherlock.com/faq.html

-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }
</#if>

<#if application.nineOldAndroids>
# -- Nine Old Androids --
# same configs as ABS from http://actionbarsherlock.com/faq.html just changed package

-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
</#if>

<#if application.acra>
# -- ACRA --
# from https://github.com/ACRA/acra/wiki/Proguard

# Required to display line numbers and so in ACRA reports
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# keep this class so that logging will show 'ACRA' and not a obfuscated name like 'a'.
# Note: if you are removing log messages elsewhere in this file then this isn't necessary
-keep class org.acra.ACRA {
	*;
}

# keep this around for some enums that ACRA needs
-keep class org.acra.ReportingInteractionMode {
    *;
}
-keepnames class org.acra.ReportField {
    *;
}

# keep this otherwise it is removed by ProGuard
-keep public class org.acra.ErrorReporter
{
    public void addCustomData(java.lang.String,java.lang.String);
    public void putCustomData(java.lang.String,java.lang.String);
    public void removeCustomData(java.lang.String);
}

# keep this otherwise it is removed by ProGuard
-keep public class org.acra.ErrorReporter
{
    public void handleSilentException(java.lang.Throwable);
}
</#if>

<#if application.roboguice>
# -- Roboguice --
# from http://code.google.com/p/roboguice/wiki/ProGuard

-keep class com.google.inject.Binder
-keepclassmembers class * {
    @com.google.inject.Inject <init>(...);
}

-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }
-keep class roboguice.** { *; }

# There's no way to keep all @Observes methods, so use the On*Event convention to identify event handlers
-keepclassmembers class * { 
    void *(**On*Event);
}

-dontwarn roboguice.**

</#if>

<#if application.restTemplate>
# -- Rest Template --

-keepclassmembers public class org.springframework {
   public *;
}

-dontwarn org.springframework.http.**

</#if>

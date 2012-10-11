<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="src" path="gen"/>
	<classpathentry kind="con" path="com.android.ide.eclipse.adt.ANDROID_FRAMEWORK"/>
	<classpathentry kind="con" path="com.android.ide.eclipse.adt.LIBRARIES"/>
	<#if State.androidAnnotations>
	<classpathentry exported="true" kind="lib" path="libs/androidannotations-2.6-api.jar"/>
	<classpathentry kind="src" path=".apt_generated">
		<attributes>
			<attribute name="optional" value="true"/>
		</attributes>
	</classpathentry>
	</#if>
	<classpathentry kind="output" path="bin/classes"/>
</classpath>

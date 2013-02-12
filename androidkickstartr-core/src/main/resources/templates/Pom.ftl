<?xml version="1.0" encoding="UTF-8"?>
<project
	xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd"
>
	<modelVersion>4.0.0</modelVersion>
	<groupId>${application.packageName}</groupId>
	<artifactId>${application.name}</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>apk</packaging>
	<name>${application.name}</name>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

		<!-- Plugins -->
		<android-maven-plugin.version>3.5.0</android-maven-plugin.version>
		<maven-compiler-plugin.version>2.3.2</maven-compiler-plugin.version>
		<api.platform>16</api.platform>

		<!-- Dependencies -->
		<#if application.androidAnnotations>
		<androidannotations.version>2.7</androidannotations.version>
		</#if>
		<#if application.actionBarSherlock>
		<actionBarSherlock.version>4.2.0</actionBarSherlock.version>
		</#if>
		<android.version>4.1.1.4</android.version>
		<#if application.viewPagerIndicator >
		<viewpagerindicator.version>2.4.0</viewpagerindicator.version>
		</#if>
		<#if application.nineOldAndroids>
		<nineoldandroids.version>2.4.0</nineoldandroids.version>
		</#if>
		<#if application.supportV4>
		<support-v4.version>r7</support-v4.version>
		</#if>
		<#if application.restTemplate>
		<spring-android-rest-template.version>1.0.0.RELEASE</spring-android-rest-template.version>
		</#if>
		<#if application.acra>
		<acra.version>4.4.0</acra.version>
		</#if>
		<#if application.roboguice>
		<roboguice.version>2.0</roboguice.version>
		</#if>
	</properties>

	<dependencies>
		<dependency>
			<groupId>com.google.android</groupId>
			<artifactId>android</artifactId>
			<scope>provided</scope>
			<#noparse>
			<version>${android.version}</version>
			</#noparse>
		</dependency>
		<#if application.androidAnnotations>
		<dependency>
			<groupId>com.googlecode.androidannotations</groupId>
			<artifactId>androidannotations</artifactId>
			<#noparse>
			<version>${androidannotations.version}</version>
			</#noparse>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>com.googlecode.androidannotations</groupId>
			<artifactId>androidannotations-api</artifactId>
			<#noparse>
			<version>${androidannotations.version}</version>
			</#noparse>
		</dependency>
		</#if>
		<#if application.supportV4>
		<dependency>
			<groupId>com.google.android</groupId>
			<artifactId>support-v4</artifactId>
			<#noparse>
			<version>${support-v4.version}</version>
			</#noparse>
		</dependency>
		</#if>
		<#if application.actionBarSherlock>
		<dependency>
			<groupId>com.actionbarsherlock</groupId>
			<artifactId>actionbarsherlock</artifactId>
			<#noparse>
			<version>${actionBarSherlock.version}</version>
			</#noparse>
			<type>apklib</type>
		</dependency>
		<dependency>
			<groupId>com.actionbarsherlock</groupId>
			<artifactId>actionbarsherlock</artifactId>
			<#noparse>
			<version>${actionBarSherlock.version}</version>
			</#noparse>
			<type>jar</type>
			<scope>provided</scope>
		</dependency>
		</#if>
		<#if application.viewPagerIndicator>
		<dependency>
			<groupId>com.viewpagerindicator</groupId>
			<artifactId>library</artifactId>
			<#noparse>
			<version>${viewpagerindicator.version}</version>
			</#noparse>
			<type>apklib</type>
		</dependency>
		</#if>
		<#if application.nineOldAndroids>
		<dependency>
			<groupId>com.nineoldandroids</groupId>
			<artifactId>library</artifactId>
			<#noparse>
			<version>${nineoldandroids.version}</version>
			</#noparse>
		</dependency>
		</#if>
		<#if application.restTemplate>
		<dependency>
			<groupId>org.springframework.android</groupId>
			<artifactId>spring-android-rest-template</artifactId>
			<#noparse>
			<version>${spring-android-rest-template.version}</version>
			</#noparse>
		</dependency>
		</#if>
		<#if application.acra>
		<dependency>
			<groupId>ch.acra</groupId>
			<artifactId>acra</artifactId>
			<#noparse>
			<version>${acra.version}</version>
			</#noparse>
		</dependency>
		</#if>
		<#if application.roboguice>
		<dependency>
            <groupId>org.roboguice</groupId>
            <artifactId>roboguice</artifactId>
			<#noparse>
			<version>${roboguice.version}</version>
			</#noparse>
		</dependency>
		</#if>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>com.jayway.maven.plugins.android.generation2</groupId>
				<artifactId>android-maven-plugin</artifactId>
				<#noparse>
				<version>${android-maven-plugin.version}</version>
				</#noparse>
				<extensions>true</extensions>
				<configuration>
					<sdk>
						<#noparse>
						<platform>${api.platform}</platform>
						</#noparse>
					</sdk>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<#noparse>
				<version>${maven-compiler-plugin.version}</version>
				</#noparse>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
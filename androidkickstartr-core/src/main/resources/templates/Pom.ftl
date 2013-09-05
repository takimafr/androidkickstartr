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
		<android-maven-plugin.version>3.6.0</android-maven-plugin.version>
		<maven-compiler-plugin.version>2.3.2</maven-compiler-plugin.version>
		<api.platform>16</api.platform>

		<!-- Dependencies -->
		<android.version>4.1.1.4</android.version>
        <#list application.libraries as lib>
            <${lib.name}.version>${lib.version}</${lib.name}.version>
        </#list>
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
        <#list application.libraries as lib>
        <#list lib.mavenDeps as mvn>
		<dependency>
			<groupId>${mvn.groupId}</groupId>
			<artifactId>${mvn.artifactId}</artifactId>
			<version><#noparse>${</#noparse>${lib.name}.version<#noparse>}</#noparse></version>
            <#if mvn.type??><type>${mvn.type}</type></#if>
            <#if mvn.scope??><scope>${mvn.scope}</scope></#if>
		</dependency>
        </#list>
        </#list>
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
					<#if application.proguard>
					<!-- Proguard is not skipped by default -->
					<proguard>
						<skip>false</skip>
					</proguard>
					</#if>
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
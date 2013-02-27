package com.athomas.androidkickstartr;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.athomas.androidkickstartr.AppDetails.Builder;

/**
 * Requires a device or emulator connected
 * 
 */

public class KickstartrMavenTest {

	private Kickstartr kickstartr;
	private AppDetails appDetails;
	private Builder builder;

	@Before
	public void before() {
		builder = new AppDetails.Builder().//
				packageName("com.androidkickstartr.app").//
				name("MyApp").//
				activity("MainActivity").//
				activityLayout("activity_main").//
				minSdk(8).//
				targetSdk(16).//
				permissions(new ArrayList<String>());
	}

	@After
	public void cleanProject() {
		buildWithMaven();
		kickstartr.clean();
	}

	private void buildWithMaven() {
		InvocationRequest request = new DefaultInvocationRequest();

		File pom = new File("generated/MyApp-AndroidKickstartr/MyApp/pom.xml");
		Assert.assertNotNull(pom);
		Assert.assertTrue(pom.exists());
		request.setPomFile(pom);

		File baseDir = new File("generated/MyApp-AndroidKickstartr/MyApp");
		Assert.assertNotNull(baseDir);
		Assert.assertTrue(baseDir.exists());
		request.setBaseDirectory(baseDir);

		ArrayList<String> goals = new ArrayList<String>();
		goals.add("clean");
		goals.add("install");
		goals.add("android:deploy");
		request.setGoals(goals);
		request.setShowErrors(true);
		request.setDebug(false);

		DefaultInvoker invoker = new DefaultInvoker();
		String mavenHome = System.getenv("MAVEN_HOME");

		if (mavenHome == null)
			throw new NoSuchElementException("Compilation impossible because you do not have a $MAVEN_HOME variable declared in your environment.");

		invoker.setMavenHome(new File(mavenHome));
		InvocationResult result;
		try {
			result = invoker.execute(request);

			Assert.assertTrue(result.getExitCode() == 0);
			Assert.assertTrue(result.getExecutionException() == null);
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generateProject_maven() {
		appDetails = builder.//
				maven(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_proguard() {
		appDetails = builder.//
				maven(true). //
				proguard(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_viewpager() {
		appDetails = builder.//
				maven(true). //
				viewPager(true). //
				supportV4(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				tabNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				listNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_aa() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_aa_rest() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_aa_rest_acra() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_roboguice() {
		appDetails = builder.//
				maven(true). //
				roboguice(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_roboguice_abs() {
		appDetails = builder.//
				maven(true). //
				roboguice(true). //
				actionBarSherlock(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_roboguice_viewpager() {
		appDetails = builder.//
				maven(true). //
				roboguice(true). //
				viewPager(true). //
				supportV4(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_roboguice_abs_viewpager() {
		appDetails = builder.//
				maven(true). //
				roboguice(true).//
				actionBarSherlock(true).//
				viewPager(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa_rest_acra() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa_rest_acra_nine() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa_rest_acra_nine_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab_aa_rest_acra_nine_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				tabNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab_rest_acra_nine_viewpager_roboguice() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				tabNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				roboguice(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_aa_rest_acra_nine_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				listNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_rest_acra_nine_viewpager_roboguice() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				listNavigation(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				roboguice(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_aa_rest_acra_nine_viewpager_proguard() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				listNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				proguard(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_rest_acra_nine_viewpager_roboguice_proguard() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				listNavigation(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				roboguice(true). //
				proguard(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	private File launchKickstartr() {
		kickstartr = new Kickstartr(appDetails);
		return kickstartr.startZip();
	}

}

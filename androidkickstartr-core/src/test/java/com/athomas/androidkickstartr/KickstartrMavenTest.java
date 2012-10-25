package com.athomas.androidkickstartr;

import java.io.File;
import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;

public class KickstartrMavenTest {

	private static Application application;

	private Kickstartr kickstartr;
	private State state;

	@Before
	public void tearUp() {
		application = new Application.Builder().//
				packageName("com.androidkickstartr.app").//
				name("MyApp").//
				activity("MainActivity").//
				activityLayout("activity_main").//
				minSdk(8).//
				targetSdk(16).//
				permissions(new ArrayList<String>()).//
				build();
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
		state = new State.Builder().//
				maven(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_viewpager() {
		state = new State.Builder().//
				maven(true). //
				viewPager(true). //
				supportV4(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}
	
	@Test
	public void generateProject_maven_abs() {
		state = new State.Builder().//
				maven(true). //
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}
	
	@Test
	public void generateProject_maven_abs_viewpager() {
		state = new State.Builder().//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab() {
		state = new State.Builder().//
				maven(true). //
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_tab_viewpager() {
		state = new State.Builder().//
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
		state = new State.Builder().//
				maven(true). //
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_list_viewpager() {
		state = new State.Builder().//
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
		state = new State.Builder().//
				maven(true). //
				androidAnnotations(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa() {
		state = new State.Builder().//
				maven(true). //
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_aa_rest() {
		state = new State.Builder().//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_aa_rest_acra() {
		state = new State.Builder().//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_maven_abs_aa_rest_acra() {
		state = new State.Builder().//
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
		state = new State.Builder().//
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
		state = new State.Builder().//
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

	private File launchKickstartr() {
		kickstartr = new Kickstartr(state, application);
		return kickstartr.start();
	}

}

package com.athomas.androidkickstartr;

import java.io.File;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;

public class KickstartrNonMavenTest {

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
		kickstartr.clean();
	}

	@Test
	public void generateProject() {
		state = new State.Builder().//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);

	}

	@Test
	public void generateProject_proguard() {
		state = new State.Builder().//
				proguard(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);

	}

	@Test
	public void generateProject_viewpager() {
		state = new State.Builder().//
				viewPager(true). //
				supportV4(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_viewpager() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_tab() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_tab_viewpager() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				tabNavigation(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_list() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_list_viewpager() {
		state = new State.Builder().//
				actionBarSherlock(true).//
				listNavigation(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa() {
		state = new State.Builder().//
				androidAnnotations(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_aa() {
		state = new State.Builder().//
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa_rest() {
		state = new State.Builder().//
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa_rest_acra() {
		state = new State.Builder().//
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice() {
		state = new State.Builder().//
				roboguice(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_abs() {
		state = new State.Builder().//
				roboguice(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_viewpager() {
		state = new State.Builder().//
				roboguice(true).//
				viewPager(true).//
				supportV4(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_abs_viewpager() {
		state = new State.Builder().//
				roboguice(true).//
				actionBarSherlock(true).//
				viewPager(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_aa_rest_acra() {
		state = new State.Builder().//
				actionBarSherlock(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_aa_rest_acra_nine() {
		state = new State.Builder().//
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
	public void generateProject_abs_aa_rest_acra_nine_viewpager() {
		state = new State.Builder().//
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
	public void generateProject_abs_tab_aa_rest_acra_nine_viewpager() {
		state = new State.Builder().//
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
	public void generateProject_abs_tab_rest_acra_nine_viewpager_roboguice() {
		state = new State.Builder().//
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
	public void generateProject_abs_list_aa_rest_acra_nine_viewpager() {
		state = new State.Builder().//
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
	public void generateProject_abs_list_rest_acra_nine_viewpager_roboguice() {
		state = new State.Builder().//
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
	public void generateProject_abs_list_aa_rest_acra_nine_viewpager_proguard() {
		state = new State.Builder().//
				actionBarSherlock(true). //
				listNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				proguard(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_list_rest_acra_nine_viewpager_roboguice_proguard() {
		state = new State.Builder().//
				actionBarSherlock(true). //
				listNavigation(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				roboguice(true). //
				proguard(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	private File launchKickstartr() {
		kickstartr = new Kickstartr(state, application);
		return kickstartr.start();
	}

}

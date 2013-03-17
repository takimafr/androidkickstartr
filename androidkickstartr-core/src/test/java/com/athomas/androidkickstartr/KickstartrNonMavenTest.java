package com.athomas.androidkickstartr;

import java.io.File;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.athomas.androidkickstartr.AppDetails.Builder;

/**
 * Requires a device or emulator connected
 * 
 */

public class KickstartrNonMavenTest {

	private Kickstartr kickstartr;
	private AppDetails appDetails;
	private Builder builder;

	@Before
	public void tearUp() {
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
		kickstartr.clean();
	}

	@Test
	public void generateProject() {
		appDetails = builder.build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);

	}

	@Test
	public void generateProject_proguard() {
		appDetails = builder.//
				proguard(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);

	}

	@Test
	public void generateProject_viewpager() {
		appDetails = builder.//
				viewPager(true). //
				supportV4(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs() {
		appDetails = builder.//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_tab() {
		appDetails = builder.//
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_tab_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				tabNavigation(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_list() {
		appDetails = builder.//
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_list_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				listNavigation(true). //
				viewPager(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa() {
		appDetails = builder.//
				androidAnnotations(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_aa() {
		appDetails = builder.//
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa_rest() {
		appDetails = builder.//
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_aa_rest_acra() {
		appDetails = builder.//
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice() {
		appDetails = builder.//
				roboguice(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_abs() {
		appDetails = builder.//
				roboguice(true).//
				actionBarSherlock(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_viewpager() {
		appDetails = builder.//
				roboguice(true).//
				viewPager(true).//
				supportV4(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_roboguice_abs_viewpager() {
		appDetails = builder.//
				roboguice(true).//
				actionBarSherlock(true).//
				viewPager(true).//
				build();

		File file = launchKickstartr();
		Assert.assertNotNull(file);
	}

	@Test
	public void generateProject_abs_aa_rest_acra() {
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		appDetails = builder.//
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
		kickstartr = new Kickstartr(appDetails);
		return kickstartr.zipify();
	}

}

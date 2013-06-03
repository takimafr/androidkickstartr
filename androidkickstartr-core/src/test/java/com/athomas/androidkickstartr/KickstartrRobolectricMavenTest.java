package com.athomas.androidkickstartr;

import org.junit.Test;

/**
 * Requires a device or emulator connected
 * 
 */
public class KickstartrRobolectricMavenTest extends AbstractKickstartrTest {

	@Override
	public void cleanProject() {
		buildWithMaven();
		super.cleanProject();
	}

	@Test
	public void generateProject_maven_abs_tab_robolectric() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				tabNavigation(true). //
				robolectric(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_robolectric() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				listNavigation(true). //
				robolectric(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_tab_aa_robolectric() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				tabNavigation(true). //
				androidAnnotations(true). //
				robolectric(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_robolectric() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				robolectric(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_robolectric() {
		appDetails = builder.//
				maven(true). //
				robolectric(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_aa_rest_acra_robolectric() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				robolectric(true).//
				acra(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_roboguice_robolectric() {
		appDetails = builder.//
				maven(true). //
				roboguice(true).//
				robolectric(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_aa_rest_acra_nine_viewpager_robolectric() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true). //
				listNavigation(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				nineOldAndroids(true). //
				viewPager(true). //
				robolectric(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_aa_rest_acra_nine_viewpager_proguard_robolectric() {
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
				robolectric(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_rest_acra_nine_viewpager_roboguice_proguard_robolectric() {
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
				robolectric(true).//
				build();

		testMaven();
	}

}

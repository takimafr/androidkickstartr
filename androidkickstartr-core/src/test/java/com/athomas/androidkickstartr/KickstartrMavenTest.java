/**
 * Copyright (C) 2012-2013 eBusiness Information (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.athomas.androidkickstartr;

import org.junit.Test;

/**
 * Requires a device or emulator connected
 * 
 */

public class KickstartrMavenTest extends AbstractKickstartrTest {

	@Override
	public void cleanProject() {
		buildWithMaven();
		super.cleanProject();
	}

	@Test
	public void generateProject_maven() {
		appDetails = builder.//
				maven(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_proguard() {
		appDetails = builder.//
				maven(true). //
				proguard(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_viewpager() {
		appDetails = builder.//
				maven(true). //
				viewPager(true). //
				supportV4(true). //
				build();

		testMaven();
	}


    @Test
    public void generateProject_maven_eventbus() {
        appDetails = builder.//
                maven(true). //
                eventbus(true). //
                build();

        testMaven();
    }

	@Test
	public void generateProject_maven_abs() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_sample() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				sample(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_tab() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_tab_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				tabNavigation(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_viewpager() {
		appDetails = builder.//
				maven(true). //
				actionBarSherlock(true).//
				viewPager(true). //
				listNavigation(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_aa() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_aa() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_aa_rest() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_aa_rest_acra() {
		appDetails = builder.//
				maven(true). //
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_roboguice() {
		appDetails = builder.//
				maven(true). //
				roboguice(true).//
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_roboguice_abs() {
		appDetails = builder.//
				maven(true). //
				roboguice(true). //
				actionBarSherlock(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_roboguice_viewpager() {
		appDetails = builder.//
				maven(true). //
				roboguice(true). //
				viewPager(true). //
				supportV4(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_roboguice_abs_viewpager() {
		appDetails = builder.//
				maven(true). //
				roboguice(true).//
				actionBarSherlock(true).//
				viewPager(true).//
				build();

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
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

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_aa_rest_acra_nine_viewpager_proguard_sample() {
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
				sample(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_rest_acra_nine_viewpager_roboguice_proguard_eventbus() {
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
                eventbus(true). //
				build();

		testMaven();
	}

	@Test
	public void generateProject_maven_abs_list_rest_acra_nine_viewpager_roboguice_proguard_eventbus_iconify_sample() {
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
                eventbus(true). //
                iconify(true). //
				sample(true). //
				build();

		testMaven();
	}

}

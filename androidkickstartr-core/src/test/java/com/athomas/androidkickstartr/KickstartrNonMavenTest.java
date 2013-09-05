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

public class KickstartrNonMavenTest extends AbstractKickstartrTest {

	@Test
	public void generateProject() {
		appDetails = builder.build();

		testNonMaven();
	}

	@Test
	public void generateProject_proguard() {
		appDetails = builder.//
				proguard(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_viewpager() {
		appDetails = builder.//
				viewPager(true). //
				supportV4(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs() {
		appDetails = builder.//
				actionBarSherlock(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				viewPager(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_tab() {
		appDetails = builder.//
				actionBarSherlock(true).//
				tabNavigation(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_tab_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				tabNavigation(true). //
				viewPager(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_list() {
		appDetails = builder.//
				actionBarSherlock(true).//
				listNavigation(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_list_viewpager() {
		appDetails = builder.//
				actionBarSherlock(true).//
				listNavigation(true). //
				viewPager(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_aa() {
		appDetails = builder.//
				androidAnnotations(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_aa() {
		appDetails = builder.//
				androidAnnotations(true).//
				actionBarSherlock(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_aa_rest() {
		appDetails = builder.//
				androidAnnotations(true).//
				restTemplate(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_aa_rest_acra() {
		appDetails = builder.//
				androidAnnotations(true).//
				restTemplate(true).//
				acra(true). //
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_roboguice() {
		appDetails = builder.//
				roboguice(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_roboguice_abs() {
		appDetails = builder.//
				roboguice(true).//
				actionBarSherlock(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_roboguice_viewpager() {
		appDetails = builder.//
				roboguice(true).//
				viewPager(true).//
				supportV4(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_roboguice_abs_viewpager() {
		appDetails = builder.//
				roboguice(true).//
				actionBarSherlock(true).//
				viewPager(true).//
				build();

		testNonMaven();
	}

	@Test
	public void generateProject_abs_aa_rest_acra() {
		appDetails = builder.//
				actionBarSherlock(true). //
				androidAnnotations(true). //
				restTemplate(true).//
				acra(true). //
				build();

		testNonMaven();
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

		testNonMaven();
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

		testNonMaven();
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

		testNonMaven();
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

		testNonMaven();
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

		testNonMaven();
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

		testNonMaven();
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

        testNonMaven();
    }
    @Test
    public void generateProject_abs_list_aa_rest_acra_nine_viewpager_eventbus_proguard() {
        appDetails = builder.//
                actionBarSherlock(true). //
                listNavigation(true). //
                androidAnnotations(true). //
                restTemplate(true).//
                acra(true). //
                nineOldAndroids(true). //
                eventbus(true). //
                viewPager(true). //
                proguard(true).//
                build();

        testNonMaven();
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

		testNonMaven();
	}

}

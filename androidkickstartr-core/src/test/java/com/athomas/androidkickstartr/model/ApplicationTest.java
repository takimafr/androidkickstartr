/**
 * Copyright (C) 2012-2013 eBusiness Information, Excilys Group (www.excilys.com)
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
package com.athomas.androidkickstartr.model;

import org.junit.Test;

import com.athomas.androidkickstartr.AppDetails;
import com.athomas.androidkickstartr.AppDetails.Builder;

public class ApplicationTest {

	@Test
	public void packageName() {
		Builder builder = new AppDetails.Builder();

		builder.packageName("com.athomas.androidkickstartr");
		builder.packageName("com.athomas.AndroidKickstartR");
		builder.packageName("com.athomas1.androidkickstartr2");
		builder.packageName("com.athomas1._2androidkickstartr2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid1() {
		new AppDetails.Builder().packageName("com.athomas.androidkickstartr.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid2() {
		new AppDetails.Builder().packageName(".com.athomas.androidkickstartr");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid3() {
		new AppDetails.Builder().packageName(".com.athomas.androidkickstartr..");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid4() {
		new AppDetails.Builder().packageName(".com.athomas..androidkickstartr");
	}

	@Test
	public void name() throws Exception {
		Builder builder = new AppDetails.Builder();

		builder.name("AndroidKickstartR");
		builder.name("androidkickstartr");
		builder.name("androidkickstartr1");
		builder.name("androidkickstartr_");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid1() {
		new AppDetails.Builder().name("AndroidKickstartR-");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid2() {
		new AppDetails.Builder().name("AndroidKickstartR_éà");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid3() {
		new AppDetails.Builder().name("AndroidKickstartR@");
	}

	@Test
	public void activity() throws Exception {
		Builder builder = new AppDetails.Builder();

		builder.activity("AndroidKickstartR");
		builder.activity("androidkickstartr");
		builder.activity("androidkickstartr1");
		builder.activity("androidkickstartr_");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid1() {
		new AppDetails.Builder().activity("AndroidKickstartR-");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid2() {
		new AppDetails.Builder().activity("AndroidKickstartR_éà");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid3() {
		new AppDetails.Builder().activity("AndroidKickstartR@");
	}

}

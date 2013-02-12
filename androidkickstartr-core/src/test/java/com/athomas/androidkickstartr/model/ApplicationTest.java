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

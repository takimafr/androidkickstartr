package com.athomas.androidkickstartr.model;

import org.junit.Test;

import com.athomas.androidkickstartr.model.Application.Builder;

public class ApplicationTest {

	@Test
	public void packageName() {
		Builder builder = new Application.Builder();

		builder.packageName("com.athomas.androidkickstartr");
		builder.packageName("com.athomas.AndroidKickstartR");
		builder.packageName("com.athomas1.androidkickstartr2");
		builder.packageName("com.athomas1._2androidkickstartr2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid1() {
		new Application.Builder().packageName("com.athomas.androidkickstartr.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid2() {
		new Application.Builder().packageName(".com.athomas.androidkickstartr");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid3() {
		new Application.Builder().packageName(".com.athomas.androidkickstartr..");
	}

	@Test(expected = IllegalArgumentException.class)
	public void packageName_invalid4() {
		new Application.Builder().packageName(".com.athomas..androidkickstartr");
	}

	@Test
	public void name() throws Exception {
		Builder builder = new Application.Builder();

		builder.name("AndroidKickstartR");
		builder.name("androidkickstartr");
		builder.name("androidkickstartr1");
		builder.name("androidkickstartr_");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid1() {
		new Application.Builder().name("AndroidKickstartR-");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid2() {
		new Application.Builder().name("AndroidKickstartR_éà");
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_invalid3() {
		new Application.Builder().name("AndroidKickstartR@");
	}

	@Test
	public void activity() throws Exception {
		Builder builder = new Application.Builder();

		builder.activity("AndroidKickstartR");
		builder.activity("androidkickstartr");
		builder.activity("androidkickstartr1");
		builder.activity("androidkickstartr_");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid1() {
		new Application.Builder().activity("AndroidKickstartR-");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid2() {
		new Application.Builder().activity("AndroidKickstartR_éà");
	}

	@Test(expected = IllegalArgumentException.class)
	public void activity_invalid3() {
		new Application.Builder().activity("AndroidKickstartR@");
	}

}

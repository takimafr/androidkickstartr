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

import com.athomas.androidkickstartr.AppDetails.Builder;

public abstract class AbstractKickstartrTest {

	private static final String TARGET_DIR_NAME = "generated";

	protected Kickstartr kickstartr;
	protected AppDetails appDetails;
	protected Builder builder;

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

	protected void buildWithMaven() {
		InvocationRequest request = new DefaultInvocationRequest();

		File pom = new File(TARGET_DIR_NAME + "/MyApp-AndroidKickstartr/MyApp/pom.xml");
		Assert.assertNotNull(pom);
		Assert.assertTrue(pom.exists());
		request.setPomFile(pom);

		File baseDir = new File(TARGET_DIR_NAME + "/MyApp-AndroidKickstartr/MyApp");
		Assert.assertNotNull(baseDir);
		Assert.assertTrue(baseDir.exists());
		request.setBaseDirectory(baseDir);

		ArrayList<String> goals = new ArrayList<String>();
		goals.add("clean");
		goals.add("install");
		goals.add("android:deploy");
		goals.add("android:run");
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

	public void testMaven() {
		kickstartr = new Kickstartr(appDetails, TARGET_DIR_NAME);
		kickstartr.clean(); // We should also clean before starting, in case last build failed
		File file = kickstartr.zipify();
		Assert.assertNotNull(file);
	}

	public void testNonMaven() {
		kickstartr = new Kickstartr(appDetails);
		File file = kickstartr.zipify();
		Assert.assertNotNull(file);
	}

	@After
	public void cleanProject() {
		kickstartr.clean();
	}

}

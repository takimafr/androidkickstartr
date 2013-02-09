package com.athomas.androidkickstartr.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

	private static final Pattern PATTERN_PACKAGE = Pattern.compile("^[a-z_]\\w*(\\.[a-z_]\\w*)*$",
			Pattern.CASE_INSENSITIVE);
	private static final Pattern PATTERN_APPLICATION_NAME = Pattern.compile("^\\w+$");
	private static final Pattern PATTERN_ACTIVITY = PATTERN_APPLICATION_NAME;
	private static final Pattern PATTERN_ACTIVITY_LAYOUT = PATTERN_ACTIVITY;

	private String packageName;
	private String name;
	private int minSdk;
	private int targetSdk;
	private List<String> permissions;
	private String activity;
	private String activityLayout;

	public String getPackageName() {
		return packageName;
	}

	public String getName() {
		return name;
	}

	public int getMinSdk() {
		return minSdk;
	}

	public int getTargetSdk() {
		return targetSdk;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getActivity() {
		return activity;
	}

	public String getActivityLayout() {
		return activityLayout;
	}

	public String getActivityPackage() {
		return packageName + "." + activity;
	}

	public String getRestClientPackage() {
		return packageName + ".rest.RestClient";
	}

	public String getRestClientName() {
		return "RestClient";
	}

	public String getR() {
		return packageName + ".R";
	}

	public String getViewPagerAdapterPackage() {
		return packageName + ".adapter.ViewPagerAdapter";
	}

	public String getSampleFragmentPackage() {
		return packageName + ".SampleFragment";
	}

	public String getRoboSherlockActivityPackage() {
		return packageName + ".robosherlock.RoboSherlockActivity";
	}

	public String getRoboSherlockFragmentPackage() {
		return packageName + ".robosherlock.RoboSherlockFragment";
	}

	public String getRoboSherlockFragmentActivityPackage() {
		return packageName + ".robosherlock.RoboSherlockFragmentActivity";
	}

	public String getRestTemplateUrl() {
		return "http://10.0.0.2";
	}

	public String getApplicationPackage() {
		return packageName + "." + getApplicationClassName();
	}

	public String getApplicationClassName() {
		return name + "Application";
	}

	@Override
	public String toString() {
		return "Application [packageName=" + packageName + //
				", name=" + name + //
				", minSdk=" + minSdk + //
				", targetSdk=" + targetSdk + //
				", permissions=" + permissions + //
				", activity=" + activity + //
				", activityLayout=" + activityLayout + //
				"]";
	}

	public static class Builder {

		private Application application;

		public Builder() {
			application = new Application();
		}

		public Builder packageName(String packageName) {
			Matcher matcher = PATTERN_PACKAGE.matcher(packageName);
			if (!matcher.matches())
				throw new IllegalArgumentException("Package name '" + packageName + "' is invalid");

			application.packageName = packageName;
			return this;
		}

		public Builder name(String name) {
			Matcher matcher = PATTERN_APPLICATION_NAME.matcher(name);
			if (!matcher.matches())
				throw new IllegalArgumentException("Application name '" + name + "' is invalid");

			application.name = name;
			return this;
		}

		public Builder minSdk(int minSdk) {
			application.minSdk = minSdk;
			return this;
		}

		public Builder targetSdk(int targetSdk) {
			application.targetSdk = targetSdk;
			return this;
		}

		public Builder permissions(List<String> permissions) {
			application.permissions = permissions;
			return this;
		}

		public Builder activity(String activity) {
			Matcher matcher = PATTERN_ACTIVITY.matcher(activity);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity name '" + activity + "' is invalid");

			application.activity = activity;
			return this;
		}

		public Builder activityLayout(String activityLayout) {
			Matcher matcher = PATTERN_ACTIVITY_LAYOUT.matcher(activityLayout);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity layout name '" + activityLayout + "' is invalid");

			application.activityLayout = activityLayout;
			return this;
		}

		public Application build() {
			return application;
		}
	}

}

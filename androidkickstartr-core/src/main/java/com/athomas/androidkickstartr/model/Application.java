package com.athomas.androidkickstartr.model;

import java.util.List;

public class Application {

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
			application.packageName = packageName;
			return this;
		}

		public Builder name(String name) {
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
			application.activity = activity;
			return this;
		}

		public Builder activityLayout(String activityLayout) {
			application.activityLayout = activityLayout;
			return this;
		}

		public Application build() {
			return application;
		}
	}

}

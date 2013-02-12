package com.athomas.androidkickstartr;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppDetails {

	private static final Pattern PATTERN_PACKAGE = Pattern.compile("^[a-z_]\\w*(\\.[a-z_]\\w*)*$", Pattern.CASE_INSENSITIVE);
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

	private boolean actionBarSherlock;
	private boolean listNavigation;
	private boolean tabNavigation;
	private boolean viewPager;
	private boolean viewPagerIndicator;
	private boolean roboguice;
	private boolean androidAnnotations;
	private boolean restTemplate;
	private boolean maven;
	private boolean nineOldAndroids;
	private boolean supportV4;
	private boolean acra;
	private boolean eclipse;

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

	public boolean isActionBarSherlock() {
		return actionBarSherlock;
	}

	public boolean isListNavigation() {
		return listNavigation;
	}

	public boolean isTabNavigation() {
		return tabNavigation;
	}

	public boolean isViewPager() {
		return viewPager;
	}

	public boolean isViewPagerIndicator() {
		return viewPagerIndicator;
	}

	public boolean isRoboguice() {
		return roboguice;
	}

	public boolean isAndroidAnnotations() {
		return androidAnnotations;
	}

	public boolean isRestTemplate() {
		return restTemplate;
	}

	public boolean isMaven() {
		return maven;
	}

	public boolean isNineOldAndroids() {
		return nineOldAndroids;
	}

	public boolean isSupportV4() {
		return supportV4;
	}

	public boolean isAcra() {
		return acra;
	}

	public boolean isEclipse() {
		return eclipse;
	}

	@Override
	public String toString() {
		return "State [actionBarSherlock=" + actionBarSherlock + //
				", listNavigation=" + listNavigation + //
				", tabNavigation=" + tabNavigation + //
				", viewPager=" + viewPager + //
				", viewPagerIndicator=" + viewPagerIndicator + //
				", roboguice=" + roboguice + //
				", androidAnnotations=" + androidAnnotations + //
				", restTemplate=" + restTemplate + //
				", maven=" + maven + //
				", nineOldAndroids=" + nineOldAndroids + //
				", supportV4=" + supportV4 + //
				", acra=" + acra + //
				", eclipse=" + eclipse + //
				"]";

	}

	public static class Builder {

		private AppDetails state;

		public Builder() {
			state = new AppDetails();
		}

		public Builder packageName(String packageName) {
			Matcher matcher = PATTERN_PACKAGE.matcher(packageName);
			if (!matcher.matches())
				throw new IllegalArgumentException("Package name '" + packageName + "' is invalid");

			state.packageName = packageName;
			return this;
		}

		public Builder name(String name) {
			Matcher matcher = PATTERN_APPLICATION_NAME.matcher(name);
			if (!matcher.matches())
				throw new IllegalArgumentException("Application name '" + name + "' is invalid");

			state.name = name;
			return this;
		}

		public Builder minSdk(int minSdk) {
			state.minSdk = minSdk;
			return this;
		}

		public Builder targetSdk(int targetSdk) {
			state.targetSdk = targetSdk;
			return this;
		}

		public Builder permissions(List<String> permissions) {
			state.permissions = permissions;
			return this;
		}

		public Builder activity(String activity) {
			Matcher matcher = PATTERN_ACTIVITY.matcher(activity);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity name '" + activity + "' is invalid");

			state.activity = activity;
			return this;
		}

		public Builder activityLayout(String activityLayout) {
			Matcher matcher = PATTERN_ACTIVITY_LAYOUT.matcher(activityLayout);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity layout name '" + activityLayout + "' is invalid");

			state.activityLayout = activityLayout;
			return this;
		}

		public Builder actionBarSherlock(boolean actionBarSherlock) {
			state.actionBarSherlock = actionBarSherlock;
			return this;
		}

		public Builder listNavigation(boolean listNavigation) {
			state.listNavigation = listNavigation;
			return this;
		}

		public Builder tabNavigation(boolean tabNavigation) {
			state.tabNavigation = tabNavigation;
			return this;
		}

		public Builder viewPager(boolean viewPager) {
			state.viewPager = viewPager;
			return this;
		}

		public Builder viewPagerIndicator(boolean viewPagerIndicator) {
			state.viewPagerIndicator = viewPagerIndicator;
			return this;
		}

		public Builder roboguice(boolean roboguice) {
			state.roboguice = roboguice;
			return this;
		}

		public Builder androidAnnotations(boolean androidAnnotations) {
			state.androidAnnotations = androidAnnotations;
			return this;
		}

		public Builder restTemplate(boolean restTemplate) {
			state.restTemplate = restTemplate;
			return this;
		}

		public Builder maven(boolean maven) {
			state.maven = maven;
			return this;
		}

		public Builder nineOldAndroids(boolean nineOldAndroids) {
			state.nineOldAndroids = nineOldAndroids;
			return this;
		}

		public Builder supportV4(boolean supportV4) {
			state.supportV4 = supportV4;
			return this;
		}

		public Builder acra(boolean acra) {
			state.acra = acra;
			return this;
		}

		public Builder eclipse(boolean eclipse) {
			state.eclipse = eclipse;
			return this;
		}

		public AppDetails build() {

			if (state.listNavigation && state.tabNavigation) {
				throw new IllegalArgumentException("list and tab navigations must not be implemented together.");
			}

			if (!state.actionBarSherlock && (state.listNavigation || state.tabNavigation)) {
				throw new IllegalArgumentException("using listNavigation or tabNavigation needs actionBarSherlock.");
			}

			if (state.supportV4 && (state.actionBarSherlock || state.viewPagerIndicator)) {
				throw new IllegalArgumentException("support V4 is already included.");
			}

			if (state.viewPager && !state.supportV4 && !state.actionBarSherlock && !state.viewPagerIndicator) {
				throw new IllegalArgumentException("ViewPager needs support v4.");
			}

			return state;
		}
	}

}

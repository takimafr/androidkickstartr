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

import com.athomas.androidkickstartr.model.Libraries;
import com.athomas.androidkickstartr.model.Library;

import java.util.ArrayList;
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
	private boolean proguard;
	private boolean git;
	private boolean robolectric;
	private boolean sample;
    private List<Library> libraries;

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

	public String getActivityTestPackage() {
		return getActivityPackage() + "Test";
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

    public List<Library> getLibraries() {
        return libraries;
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

	public boolean isProguard() {
		return proguard;
	}

	public boolean isGit() {
		return git;
	}

	public boolean isRobolectric() {
		return robolectric;
	}

	public boolean isSample() {
		return sample;
	}

	@Override
	public String toString() {
		return "AppDetails [name=" + name + //
				", packageName=" + packageName + //
				", actionBarSherlock=" + actionBarSherlock + //
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
				", proguard=" + proguard + //
				", git=" + git + //
				", robolectric=" + robolectric + //
				", sample=" + sample + //
				"]";

	}

    public static class Builder {

		private AppDetails instance;

		public Builder() {
			instance = new AppDetails();
            instance.libraries = new ArrayList<Library>();
		}

		public Builder packageName(String packageName) {
			Matcher matcher = PATTERN_PACKAGE.matcher(packageName);
			if (!matcher.matches())
				throw new IllegalArgumentException("Package name '" + packageName + "' is invalid");

			instance.packageName = packageName;
			return this;
		}

		public Builder name(String name) {
			Matcher matcher = PATTERN_APPLICATION_NAME.matcher(name);
			if (!matcher.matches())
				throw new IllegalArgumentException("Application name '" + name + "' is invalid");

			instance.name = name;
			return this;
		}

		public Builder minSdk(int minSdk) {
			instance.minSdk = minSdk;
			return this;
		}

		public Builder targetSdk(int targetSdk) {
			instance.targetSdk = targetSdk;
			return this;
		}

		public Builder permissions(List<String> permissions) {
			instance.permissions = permissions;
			return this;
		}

		public Builder activity(String activity) {
			Matcher matcher = PATTERN_ACTIVITY.matcher(activity);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity name '" + activity + "' is invalid");

			instance.activity = activity;
			return this;
		}

		public Builder activityLayout(String activityLayout) {
			Matcher matcher = PATTERN_ACTIVITY_LAYOUT.matcher(activityLayout);
			if (!matcher.matches())
				throw new IllegalArgumentException("Activity layout name '" + activityLayout + "' is invalid");

			instance.activityLayout = activityLayout;
			return this;
		}

		public Builder actionBarSherlock(boolean actionBarSherlock) {
			instance.actionBarSherlock = actionBarSherlock;
            if (actionBarSherlock) {
                instance.libraries.add(Libraries.actionBarSherlock());
            }
			return this;
		}

		public Builder listNavigation(boolean listNavigation) {
			instance.listNavigation = listNavigation;
			return this;
		}

		public Builder tabNavigation(boolean tabNavigation) {
			instance.tabNavigation = tabNavigation;
			return this;
		}

		public Builder viewPager(boolean viewPager) {
			instance.viewPager = viewPager;
			return this;
		}

		public Builder viewPagerIndicator(boolean viewPagerIndicator) {
			instance.viewPagerIndicator = viewPagerIndicator;
            if (viewPagerIndicator) {
                instance.libraries.add(Libraries.viewPagerIndicator());
            }
			return this;
		}

		public Builder roboguice(boolean roboguice) {
			instance.roboguice = roboguice;
            if (roboguice) {
                instance.libraries.add(Libraries.roboguice());
            }
			return this;
		}

		public Builder androidAnnotations(boolean androidAnnotations) {
			instance.androidAnnotations = androidAnnotations;
            if (androidAnnotations) {
                instance.libraries.add(Libraries.androidAnnotations());
            }
			return this;
		}

		public Builder restTemplate(boolean restTemplate) {
			instance.restTemplate = restTemplate;
            if (restTemplate) {
                instance.libraries.add(Libraries.restTemplate());
            }
			return this;
		}

		public Builder maven(boolean maven) {
			instance.maven = maven;
			return this;
		}

		public Builder nineOldAndroids(boolean nineOldAndroids) {
			instance.nineOldAndroids = nineOldAndroids;
            if (nineOldAndroids) {
                instance.libraries.add(Libraries.nineOldAndroids());
            }
			return this;
		}

		public Builder supportV4(boolean supportV4) {
			instance.supportV4 = supportV4;
            if (supportV4) {
                instance.libraries.add(Libraries.supportV4());
            }
			return this;
		}

		public Builder acra(boolean acra) {
			instance.acra = acra;
            if (acra){
                instance.libraries.add(Libraries.acra());
            }
			return this;
		}

		public Builder eclipse(boolean eclipse) {
			instance.eclipse = eclipse;
			return this;
		}

		public Builder proguard(boolean proguard) {
			instance.proguard = proguard;
			return this;
		}

		public Builder git(boolean git) {
			instance.git = git;
			return this;
		}

		public Builder robolectric(boolean robolectric) {
			instance.robolectric = robolectric;

            if (robolectric) {
                instance.libraries.add(Libraries.robolectric());
                // robolectric need junit
                instance.libraries.add(Libraries.junit());
            }
			return this;
		}

        public Builder eventbus(boolean eventbus) {
            if (eventbus) {
                instance.libraries.add(Libraries.eventbus());
            }
            return this;
        }

        public Builder iconify(boolean iconify) {
            if (iconify) {
                instance.libraries.add(Libraries.iconify());
            }
            return this;
        }

        public Builder robospice(boolean robospice) {
            if (robospice) {
                instance.libraries.add(Libraries.robospice());
            }
            return this;
        }

        public Builder retrofit(boolean retrofit) {
            if (retrofit) {
                instance.libraries.add(Libraries.retrofit());
            }
            return this;
        }

        public Builder okhttp(boolean okhttp) {
            if (okhttp) {
                instance.libraries.add(Libraries.okhttp());
            }
            return this;
        }

		public Builder sample(boolean sample) {
			instance.sample = sample;
			return this;
		}

		public AppDetails build() {

			if (instance.listNavigation && instance.tabNavigation) {
				throw new IllegalArgumentException("list and tab navigations must not be implemented together.");
			}

			if (!instance.actionBarSherlock && (instance.listNavigation || instance.tabNavigation)) {
				throw new IllegalArgumentException("using listNavigation or tabNavigation needs actionBarSherlock.");
			}

			if (instance.supportV4 && (instance.actionBarSherlock || instance.viewPagerIndicator)) {
				throw new IllegalArgumentException("support V4 is already included.");
			}

			if (instance.viewPager && !instance.supportV4 && !instance.actionBarSherlock && !instance.viewPagerIndicator) {
				throw new IllegalArgumentException("ViewPager needs support v4.");
			}

			if (instance.robolectric && !instance.maven) {
				throw new IllegalArgumentException("Robolectric is only supported with Maven right now.");
			}

			return instance;
		}
	}

}

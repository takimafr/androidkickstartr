package com.athomas.androidkickstartr.model;

public class State {

	private boolean actionBarSherlock;
	private boolean listNavigation;
	private boolean tabNavigation;
	private boolean viewPager;
	private boolean viewPagerIndicator;
	private boolean androidAnnotations;
	private boolean restTemplate;
	private boolean maven;
	private boolean nineOldAndroids;
	private boolean supportV4;
	private boolean acra;
	private boolean eclipse;

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

		private State state;

		public Builder() {
			state = new State();
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

		public State build() {

			if (state.listNavigation && state.tabNavigation) {
				throw new IllegalArgumentException("list and tab navigations must not be implemented together.");
			}

			if (!state.actionBarSherlock && (state.listNavigation || state.tabNavigation)) {
				throw new IllegalArgumentException("using listNavigation or tabNavigation needs actionBarSherlock.");
			}

			if (state.supportV4 && (state.actionBarSherlock || state.viewPagerIndicator)) {
				throw new IllegalArgumentException("support V4 is already included.");
			}

			return state;
		}
	}

}

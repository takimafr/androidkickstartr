package com.athomas.androidkickstartr.util;

import java.util.HashMap;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;

public class RefHelper {

	private final JCodeModel codeModel;

	private HashMap<String, JClass> refs = new HashMap<String, JClass>();

	private JClass r;

	public RefHelper(JCodeModel codeModel) {
		this.codeModel = codeModel;
	}

	public JClass ref(String fullName) {
		JClass jClass = refs.get(fullName);
		if (jClass == null) {
			jClass = codeModel.ref(fullName);
			refs.put(fullName, jClass);
		}
		return jClass;
	}

	/**
	 * Java lang
	 */
	public JClass override() {
		return ref(Override.class.getCanonicalName());
	}

	public JClass charSequence() {
		return ref(CharSequence.class.getCanonicalName());
	}

	public JClass string() {
		return ref(String.class.getCanonicalName());
	}

	/**
	 * Android classes
	 */
	public JClass activity() {
		return ref("android.app.Activity");
	}

	public JClass viewGroup() {
		return ref("android.view.ViewGroup");
	}

	public JClass view() {
		return ref("android.view.View");
	}

	public JClass color() {
		return ref("android.graphics.Color");
	}

	public JClass textView() {
		return ref("android.widget.TextView");
	}

	public JClass bundle() {
		return ref("android.os.Bundle");
	}

	public JClass menu() {
		return ref("android.view.Menu");
	}

	public JClass context() {
		return ref("android.content.Context");
	}

	public JClass arrayAdapter() {
		return ref("android.widget.ArrayAdapter");
	}

	public JClass application() {
		return ref("android.app.Application");
	}

	public JClass gravity() {
		return ref("android.view.Gravity");
	}

	public JClass layoutInflater() {
		return ref("android.view.LayoutInflater");
	}

	public JClass r(String name) {
		r = ref(name);
		return r;
	}

	public JClass r() {
		if (r == null) {
			throw new NullPointerException("call r(String name) method at least once");
		}
		return r;
	}

	/**
	 * Android Support v4
	 */

	public JClass viewPager() {
		return ref("android.support.v4.view.ViewPager");
	}

	public JClass onPageChangeListener() {
		return ref("android.support.v4.view.ViewPager.OnPageChangeListener");
	}

	public JClass fragment() {
		return ref("android.support.v4.app.Fragment");
	}

	public JClass fragmentTransaction() {
		return ref("android.support.v4.app.FragmentTransaction");
	}

	public JClass fragmentActivity() {
		return ref("android.support.v4.app.FragmentActivity");
	}

	public JClass fragmentPagerAdapter() {
		return ref("android.support.v4.app.FragmentPagerAdapter");
	}

	public JClass fragmentManager() {
		return ref("android.support.v4.app.FragmentManager");
	}

	public JClass pagerAdapter() {
		return ref("android.support.v4.view.PagerAdapter");
	}

	/**
	 * AndroidAnnotations classes
	 */
	public JClass eactivity() {
		return ref("com.googlecode.androidannotations.annotations.EActivity");
	}

	public JClass efragment() {
		return ref("com.googlecode.androidannotations.annotations.EFragment");
	}

	public JClass viewById() {
		return ref("com.googlecode.androidannotations.annotations.ViewById");
	}

	public JClass afterViews() {
		return ref("com.googlecode.androidannotations.annotations.AfterViews");
	}

	public JClass background() {
		return ref("com.googlecode.androidannotations.annotations.Background");
	}

	public JClass uithread() {
		return ref("com.googlecode.androidannotations.annotations.UiThread");
	}

	public JClass rest() {
		return ref("com.googlecode.androidannotations.annotations.rest.Rest");
	}

	public JClass get() {
		return ref("com.googlecode.androidannotations.annotations.rest.Get");
	}

	public JClass restService() {
		return ref("com.googlecode.androidannotations.annotations.rest.RestService");
	}

	/**
	 * ActionBarSherlock classes
	 */
	public JClass sActivity() {
		return ref("com.actionbarsherlock.app.SherlockActivity");
	}

	public JClass sFragmentActivity() {
		return ref("com.actionbarsherlock.app.SherlockFragmentActivity");
	}

	public JClass sMenu() {
		return ref("com.actionbarsherlock.view.Menu");
	}

	public JClass sNavigationListener() {
		return ref("com.actionbarsherlock.app.ActionBar.OnNavigationListener");
	}

	public JClass sActionBar() {
		return ref("com.actionbarsherlock.app.ActionBar");
	}

	public JClass sTabListener() {
		return ref("com.actionbarsherlock.app.ActionBar.TabListener");
	}

	public JClass sTab() {
		return ref("com.actionbarsherlock.app.ActionBar.Tab");
	}

	/**
	 * Acra
	 */
	public JClass reportsCrashes() {
		return ref("org.acra.annotation.ReportsCrashes");
	}

	public JClass acra() {
		return ref("org.acra.ACRA");
	}

	/**
	 * Roboguice
	 */
	public JClass roboguice() {
		return ref("com.googlecode.androidannotations.annotations.RoboGuice");
	}

	public JClass injectView() {
		return ref("roboguice.inject.InjectView");
	}

	public JClass roboActivity() {
		return ref("roboguice.activity.RoboActivity");
	}

	public JClass roboFragment() {
		return ref("roboguice.fragment.RoboFragment");
	}

	public JClass roboFragmentActivity() {
		return ref("roboguice.activity.RoboFragmentActivity");
	}

	/**
	 * Spring-Android RestTemplate
	 */

	public JClass stringHttpMessageConverter() {
		return ref("org.springframework.http.converter.StringHttpMessageConverter");
	}

}

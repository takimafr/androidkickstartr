package com.athomas.androidkickstartr.generator;

import static com.sun.codemodel.JExpr.TRUE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForEach;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class MainActivityGenerator implements Generator {

	private JDefinedClass jClass;
	private RefHelper ref;
	private JCodeModel jCodeModel;
	private State state;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	private Application application;

	public MainActivityGenerator(State state, Application application) {
		this.state = state;
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException {
		this.jCodeModel = jCodeModel;

		ref = new RefHelper(jCodeModel);

		try {
			jClass = jCodeModel._class(application.getActivityPackage());

			// TODO Enhance that
			ref.r(application.getR()); // must do it at least once

			createActivity(application.getActivityLayout());

			JFieldVar textViewField = createTextViewField("hello");

			JBlock afterViewsBody = createAfterViewsMethod();
			doTextViewViewById(afterViewsBody, "hello", textViewField);

			addRestClient(textViewField);

			createOnCreateOptionsMenu();

			createConfigureViewPager(afterViewsBody, application.getViewPagerAdapterPackage());

			createConfigureActionBar(afterViewsBody);

		} catch (JClassAlreadyExistsException e1) {
			LOGGER.error("Classname already exists", e1);
		}
		return jCodeModel;
	}

	private void createActivity(String layout) {
		JClass parentActivity;

		if (state.isViewPager()) {
			parentActivity = state.isViewPager() ? ref.sFragmentActivity() : ref.fragmentActivity();
		} else {
			parentActivity = state.isActionBarSherlock() ? ref.sActivity() : ref.activity();
		}

		jClass._extends(parentActivity);

		// @EActivity
		if (state.isAndroidAnnotations()) {
			JAnnotationUse eactivityAnnotation = jClass.annotate(ref.eactivity());
			JFieldRef field = ref.r().staticRef("layout").ref(layout);
			eactivityAnnotation.param("value", field);
		}
	}

	private JFieldVar createViewField(JClass type, String name) {
		int mod = state.isAndroidAnnotations() ? JMod.NONE : JMod.PRIVATE;
		JFieldVar field = jClass.field(mod, type, name);
		return field;
	}

	private JFieldVar createTextViewField(String name) {
		return createViewField(ref.textView(), name);
	}

	private JFieldVar createViewPagerField(String name) {
		return createViewField(ref.viewPager(), name);
	}

	private JBlock createAfterViewsMethod() {
		JBlock afterViewsBody;
		if (!state.isAndroidAnnotations()) {
			JMethod onCreate = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onCreate");
			onCreate.annotate(ref.override());
			JVar params = onCreate.param(ref.bundle(), "savedInstanceState");
			afterViewsBody = onCreate.body();
			JExpression _super = JExpr._super();
			JInvocation onCreateInvocation = _super.invoke("onCreate").arg(params);
			afterViewsBody.add(onCreateInvocation);
		} else {
			JMethod afterViews = jClass.method(JMod.NONE, jCodeModel.VOID, "afterViews");
			afterViews.annotate(ref.afterViews());
			afterViewsBody = afterViews.body();
		}
		return afterViewsBody;
	}

	private void doTextViewViewById(JBlock afterViewsBody, String id, JFieldVar field) {
		doViewById(afterViewsBody, id, field, ref.textView());
	}

	private void doViewPagerViewById(JBlock afterViewsBody, String id, JFieldVar field) {
		doViewById(afterViewsBody, id, field, ref.viewPager());
	}

	private void doViewById(JBlock afterViewsBody, String id, JFieldVar field, JClass type) {
		if (!state.isAndroidAnnotations()) {
			doFindViewById(afterViewsBody, id, field, type);
		} else {
			field.annotate(ref.viewById());
		}
	}

	private void doFindViewById(JBlock afterViewsBody, String id, JFieldVar field, JClass type) {
		JFieldRef rIdHello = ref.r().staticRef("id").ref(id);
		JInvocation findViewById = JExpr.invoke("findViewById").arg(rIdHello);
		JExpression findViewByIdCasted = JExpr.cast(type, findViewById);
		afterViewsBody.assign(field, findViewByIdCasted);
	}

	// ACTION BAR SHERLOCK

	private JBlock createOnCreateOptionsMenu() {
		JMethod onCreateOptionsMenu = null;
		JClass menu = !state.isActionBarSherlock() ? ref.menu() : ref.sMenu();

		onCreateOptionsMenu = jClass.method(JMod.PUBLIC, jCodeModel.BOOLEAN, "onCreateOptionsMenu");
		JVar menuVar = onCreateOptionsMenu.param(menu, "menu");
		onCreateOptionsMenu.annotate(ref.override());
		JBlock onCreateOptionsMenuBody = onCreateOptionsMenu.body();

		String getMenuInflater = state.isActionBarSherlock() ? "getSupportMenuInflater" : "getMenuInflater";

		JFieldRef rMenuMain = ref.r().staticRef("menu").ref("activity_main");
		JInvocation inflate = JExpr.invoke(getMenuInflater).//
				invoke("inflate"). //
				arg(rMenuMain). //
				arg(menuVar);

		onCreateOptionsMenuBody.add(inflate);
		onCreateOptionsMenuBody._return(TRUE);

		return onCreateOptionsMenuBody;
	}

	private void createConfigureActionBar(JBlock afterViewsBody) {
		if (state.isActionBarSherlock() && (state.isListNavigation() || state.isTabNavigation())) {

			JMethod configureActionBar = jClass.method(JMod.PRIVATE, jCodeModel.VOID, "configureActionBar");
			JBlock configureActionBarBody = configureActionBar.body();

			// LIST NAVIGATION
			if (state.isListNavigation()) {
				addListNavigationConfiguration(configureActionBarBody);
			}

			// TAB NAVIGATION
			if (state.isTabNavigation()) {
				addTabNavigationConfiguration(configureActionBarBody);
			}
			afterViewsBody.invoke(configureActionBar);
		}
	}

	private void addTabNavigationConfiguration(JBlock configureActionBarBody) {
		// implements TabListener
		jClass._implements(ref.sTabListener());

		JClass sherlockTab = ref.sTab();
		JClass fragmentTransaction = ref.fragmentTransaction();

		// override TabListener methods
		JMethod onTabSelected = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onTabSelected");
		onTabSelected.param(sherlockTab, "tab");
		onTabSelected.param(fragmentTransaction, "ft");
		onTabSelected.annotate(ref.override());

		JMethod onTabUnselected = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onTabUnselected");
		onTabUnselected.param(sherlockTab, "tab");
		onTabUnselected.param(fragmentTransaction, "ft");
		onTabUnselected.annotate(ref.override());

		JMethod onTabReselected = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onTabReselected");
		onTabReselected.param(sherlockTab, "tab");
		onTabReselected.param(fragmentTransaction, "ft");
		onTabReselected.annotate(ref.override());

		JInvocation getSupportActionBar = JExpr.invoke("getSupportActionBar");

		JFieldRef navigationModeList = ref.sActionBar().staticRef("NAVIGATION_MODE_TABS");
		JInvocation setNavigationMode = getSupportActionBar.invoke("setNavigationMode").arg(navigationModeList);
		configureActionBarBody.add(setNavigationMode);

		JFieldRef rArrayLocations = ref.r().staticRef("array").ref("locations");
		JInvocation getResources = JExpr.invoke("getResources");
		JInvocation getStringArray = getResources.invoke("getStringArray").arg(rArrayLocations);

		JClass string = ref.string();
		JClass stringArray = string.array();
		JVar locations = configureActionBarBody.decl(stringArray, "locations", getStringArray);

		JForEach forEachLocation = configureActionBarBody.forEach(string, "location", locations);
		JVar location = forEachLocation.var();

		JBlock forEachLocationBody = forEachLocation.body();
		JInvocation newTab = getSupportActionBar.invoke("newTab");

		JVar tab = forEachLocationBody.decl(sherlockTab, "tab", newTab);
		JInvocation setText = tab.invoke("setText").arg(location);
		JInvocation setTabListener = tab.invoke("setTabListener").arg(JExpr._this());
		JInvocation addTab = getSupportActionBar.invoke("addTab").arg(tab);

		forEachLocationBody.add(setText);
		forEachLocationBody.add(setTabListener);
		forEachLocationBody.add(addTab);
	}

	private void addListNavigationConfiguration(JBlock configureActionBarBody) {
		jClass._implements(ref.sNavigationListener());

		JMethod onNavigationItemSelected = jClass.method(JMod.PUBLIC, jCodeModel.BOOLEAN, "onNavigationItemSelected");
		onNavigationItemSelected.param(jCodeModel.INT, "itemPosition");
		onNavigationItemSelected.param(jCodeModel.LONG, "itemId");
		onNavigationItemSelected.annotate(ref.override());

		JBlock body = onNavigationItemSelected.body();
		body._return(TRUE);

		// configure Tab navigation
		JInvocation getSupportActionbar = JExpr.invoke("getSupportActionBar");
		JInvocation getContext = getSupportActionbar.invoke("getThemedContext");
		JVar contextVar = configureActionBarBody.decl(ref.context(), "context");
		contextVar.init(getContext);

		JFieldRef rArrayLocations = ref.r().staticRef("array").ref("locations");
		// NEEDED! CodeModel doesn't manage the case where two
		// classes have the same name
		JExpression rLayoutSherlockSpinner = JExpr.direct("android.R.layout.simple_list_item_1");

		JClass arrayAdapter = ref.arrayAdapter();
		JInvocation createFromResource = arrayAdapter.staticInvoke("createFromResource"). //
				arg(contextVar). //
				arg(rArrayLocations). //
				arg(rLayoutSherlockSpinner);

		JClass listType = arrayAdapter.narrow(ref.charSequence());
		JVar listVar = configureActionBarBody.decl(listType, "list");
		listVar.init(createFromResource);

		JFieldRef navigationModeList = ref.sActionBar().staticRef("NAVIGATION_MODE_LIST");
		JInvocation setNavigationMode = getSupportActionbar.invoke("setNavigationMode").arg(navigationModeList);
		JInvocation setListNavigationCallbacks = getSupportActionbar.invoke("setListNavigationCallbacks").//
				arg(listVar).//
				arg(JExpr._this());

		configureActionBarBody.add(setNavigationMode);
		configureActionBarBody.add(setListNavigationCallbacks);
	}

	private void createConfigureViewPager(JBlock afterViewsBody, String viewPagerAdapterName) {
		if (state.isViewPager()) {
			JFieldVar pagerField = createViewPagerField("pager");
			doViewPagerViewById(afterViewsBody, "pager", pagerField);

			afterViewsBody.invoke("configureViewPager");

			JMethod configureViewPager = jClass.method(JMod.PRIVATE, jCodeModel.VOID, "configureViewPager");
			JBlock configureViewPagerBody = configureViewPager.body();

			JClass viewPagerAdapter = ref.ref(viewPagerAdapterName);

			JInvocation getSupportFragmentManager = JExpr.invoke("getSupportFragmentManager");
			JInvocation newViewPagerAdapter = JExpr._new(viewPagerAdapter).arg(getSupportFragmentManager);

			JVar pagerAdapter = configureViewPagerBody.decl(viewPagerAdapter, "pagerAdapter", newViewPagerAdapter);
			pagerField.invoke("setAdapter").arg(pagerAdapter);
		}
	}

	private void addRestClient(JFieldVar textViewField) {
		if (state.isRestTemplate() && state.isAndroidAnnotations()) {

			// add annotated restClient field
			JFieldVar restClient = jClass.field(JMod.NONE, ref.ref(application.getRestClientPackage()), "restClient");
			restClient.annotate(ref.restService());

			// add doSomethingElseOnUiThread method
			JMethod doSomethingElseOnUiThread = jClass.method(JMod.NONE, jCodeModel.VOID, "doSomethingElseOnUiThread");
			doSomethingElseOnUiThread.annotate(ref.uithread());

			JBlock body = doSomethingElseOnUiThread.body();
			body.invoke(textViewField, "setText").arg("Hi!");

			// add doSomethingInBackground method
			JMethod doSomethingInBackground = jClass.method(JMod.NONE, jCodeModel.VOID, "doSomethingInBackground");
			doSomethingInBackground.annotate(ref.background());
			JBlock doSomethingInBackgroundBody = doSomethingInBackground.body();

			JInvocation restClientMain = restClient.invoke("main");
			doSomethingInBackgroundBody.add(restClientMain);
			doSomethingInBackgroundBody.invoke(doSomethingElseOnUiThread);

		}
	}

}

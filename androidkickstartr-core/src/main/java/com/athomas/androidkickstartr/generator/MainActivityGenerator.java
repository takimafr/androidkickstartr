package com.athomas.androidkickstartr.generator;

import static com.sun.codemodel.JExpr.TRUE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.CodeModelHelper;
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

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private JDefinedClass jClass;
	private RefHelper ref;
	private JCodeModel jCodeModel;
	private State state;
	private Application application;

	private CodeModelHelper codeModelHelper;

	/**
	 * Code elements
	 */
	private JFieldVar pagerField;
	private JFieldVar locationsField;
	private JBlock afterViewsBody;

	public MainActivityGenerator(State state, Application application) {
		this.state = state;
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		this.jCodeModel = jCodeModel;
		this.ref = ref;
		codeModelHelper = new CodeModelHelper(ref, state);

		startGeneration(jCodeModel);
		return jCodeModel;
	}

	private void startGeneration(JCodeModel jCodeModel) {
		try {
			jClass = jCodeModel._class(application.getActivityPackage());
			
			createActivity();

			afterViewsBody = createAfterViewsMethod();

			JFieldVar textViewField = null;
			if (!state.isViewPager()) {
				textViewField = createTextViewField("hello");
				codeModelHelper.doViewById(afterViewsBody, "hello", textViewField);
			}

			if (state.isRestTemplate() && state.isAndroidAnnotations()) {
				addRestClient(textViewField);
			}

			createOnCreateOptionsMenu();

			if (state.isTabNavigation() || state.isListNavigation()) {
				createAndInitLocationsField();
			}

			if (state.isViewPager()) {
				addViewPager(jCodeModel);
			}

			createConfigureActionBar();

		} catch (JClassAlreadyExistsException e1) {
			LOGGER.error("Classname already exists", e1);
		}
	}

	private void createAndInitLocationsField() {
		// private String[] locations;
		locationsField = jClass.field(JMod.PRIVATE, ref.string().array(), "locations");
		// locations = getResources().getStringArray(R.array.locations);
		JFieldRef rArrayLocations = ref.r().staticRef("array").ref("locations");
		JInvocation getResources = JExpr.invoke("getResources");
		JInvocation getStringArray = getResources.invoke("getStringArray").arg(rArrayLocations);
		afterViewsBody.assign(locationsField, getStringArray);
	}

	private void createActivity() {
		JClass parentActivity;

		if (state.isActionBarSherlock()) {
			parentActivity = state.isViewPager() ? ref.sFragmentActivity() : ref.sActivity();
		} else {
			parentActivity = state.isViewPager() ? ref.fragmentActivity() : ref.activity();
		}

		jClass._extends(parentActivity);

		// @EActivity
		if (state.isAndroidAnnotations()) {
			JAnnotationUse eactivityAnnotation = jClass.annotate(ref.eactivity());
			JFieldRef field = ref.r().staticRef("layout").ref(application.getActivityLayout());
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

	private JBlock createAfterViewsMethod() {
		JBlock afterViewsBody;
		if (!state.isAndroidAnnotations()) {
			JMethod onCreate = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onCreate");
			onCreate.annotate(ref.override());
			JVar params = onCreate.param(ref.bundle(), "savedInstanceState");
			afterViewsBody = onCreate.body();

			// super.onCreate()
			afterViewsBody.invoke(JExpr._super(), "onCreate").arg(params);

			// setContentView(R.layout.xxx)
			afterViewsBody.invoke("setContentView").arg(ref.r().staticRef("layout").ref(application.getActivityLayout()));
		} else {
			JMethod afterViews = jClass.method(JMod.NONE, jCodeModel.VOID, "afterViews");
			afterViews.annotate(ref.afterViews());
			afterViewsBody = afterViews.body();
		}
		return afterViewsBody;
	}

	private void createOnCreateOptionsMenu() {
		JMethod onCreateOptionsMenu = null;
		JClass menu = !state.isActionBarSherlock() ? ref.menu() : ref.sMenu();

		onCreateOptionsMenu = jClass.method(JMod.PUBLIC, jCodeModel.BOOLEAN, "onCreateOptionsMenu");
		JVar menuVar = onCreateOptionsMenu.param(menu, "menu");
		onCreateOptionsMenu.annotate(ref.override());
		JBlock onCreateOptionsMenuBody = onCreateOptionsMenu.body();

		String getMenuInflater = state.isActionBarSherlock() ? "getSupportMenuInflater" : "getMenuInflater";

		JFieldRef rMenuMain = ref.r().staticRef("menu").ref("activity_main");
		onCreateOptionsMenuBody.invoke(getMenuInflater).//
				invoke("inflate"). //
				arg(rMenuMain). //
				arg(menuVar);

		onCreateOptionsMenuBody._return(TRUE);
	}

	private void createConfigureActionBar() {
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

		// @Override
		// public void onTabSelected(Tab tab, FragmentTransaction ft) {
		JMethod onTabSelectedMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onTabSelected");
		JVar tabParam = onTabSelectedMethod.param(ref.sTab(), "tab");
		onTabSelectedMethod.param(ref.fragmentTransaction(), "ft");
		onTabSelectedMethod.annotate(ref.override());

		if (state.isViewPager()) {
			JBlock onTabSelectedBody = onTabSelectedMethod.body();
			// int position = tab.getPosition();
			JVar positionVar = onTabSelectedBody.decl(jCodeModel.INT, "position", tabParam.invoke("getPosition"));
			// pager.setCurrentItem(position);
			onTabSelectedBody.invoke(pagerField, "setCurrentItem").arg(positionVar);
		}

		jClass.direct("@Override\n " + //
				"public void onTabUnselected(Tab tab, FragmentTransaction ft) {}");

		jClass.direct("@Override\n" + //
				"public void onTabReselected(Tab tab, FragmentTransaction ft) {}");

		JInvocation getSupportActionBar = JExpr.invoke("getSupportActionBar");

		JFieldRef navigationModeList = ref.sActionBar().staticRef("NAVIGATION_MODE_TABS");
		configureActionBarBody.invoke(getSupportActionBar, "setNavigationMode").arg(navigationModeList);

		JForEach forEachLocation = configureActionBarBody.forEach(ref.string(), "location", locationsField);
		JVar location = forEachLocation.var();

		JBlock forEachLocationBody = forEachLocation.body();
		JVar tab = forEachLocationBody.decl(ref.sTab(), "tab", getSupportActionBar.invoke("newTab"));
		forEachLocationBody.invoke(tab, "setText").arg(location);
		forEachLocationBody.invoke(tab, "setTabListener").arg(JExpr._this());
		forEachLocationBody.invoke(getSupportActionBar, "addTab").arg(tab);

	}

	private void addListNavigationConfiguration(JBlock configureActionBarBody) {
		jClass._implements(ref.sNavigationListener());

		// @Override
		// public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		JMethod onNavigationItemSelectedMethod = jClass.method(JMod.PUBLIC, jCodeModel.BOOLEAN, "onNavigationItemSelected");
		JVar itemPositionParam = onNavigationItemSelectedMethod.param(jCodeModel.INT, "itemPosition");
		onNavigationItemSelectedMethod.param(jCodeModel.LONG, "itemId");
		onNavigationItemSelectedMethod.annotate(ref.override());

		JBlock onNavigationItemSelectedBody = onNavigationItemSelectedMethod.body();
		if (state.isViewPager()) {
			// pager.setCurrentItem(itemPosition);
			onNavigationItemSelectedBody.invoke(pagerField, "setCurrentItem").arg(itemPositionParam);
		}
		// return true;
		onNavigationItemSelectedBody._return(JExpr.TRUE);

		// configure Tab navigation
		JInvocation getSupportActionbar = JExpr.invoke("getSupportActionBar");
		JInvocation getContext = getSupportActionbar.invoke("getThemedContext");
		JVar contextVar = configureActionBarBody.decl(ref.context(), "context", getContext);

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
		JVar listVar = configureActionBarBody.decl(listType, "list", createFromResource);

		JFieldRef navigationModeList = ref.sActionBar().staticRef("NAVIGATION_MODE_LIST");
		configureActionBarBody.invoke(getSupportActionbar, "setNavigationMode").arg(navigationModeList);
		configureActionBarBody.invoke(getSupportActionbar, "setListNavigationCallbacks").//
				arg(listVar).//
				arg(JExpr._this());
	}

	private void addRestClient(JFieldVar textViewField) {
		// add annotated restClient field
		JFieldVar restClient = jClass.field(JMod.NONE, ref.ref(application.getRestClientPackage()), "restClient");
		restClient.annotate(ref.restService());

		// add doSomethingElseOnUiThread method
		JMethod doSomethingElseOnUiThread = jClass.method(JMod.NONE, jCodeModel.VOID, "doSomethingElseOnUiThread");
		doSomethingElseOnUiThread.annotate(ref.uithread());

		JBlock body = doSomethingElseOnUiThread.body();

		if (textViewField != null) {
			body.invoke(textViewField, "setText").arg("Hi!");
		} else {
			body.directStatement("// do something on UIThread");
		}

		// add doSomethingInBackground method
		JMethod doSomethingInBackground = jClass.method(JMod.NONE, jCodeModel.VOID, "doSomethingInBackground");
		doSomethingInBackground.annotate(ref.background());
		JBlock doSomethingInBackgroundBody = doSomethingInBackground.body();

		doSomethingInBackgroundBody.invoke(restClient, "main");
		doSomethingInBackgroundBody.invoke(doSomethingElseOnUiThread);
	}

	private void addViewPager(JCodeModel jCodeModel) {
		pagerField = createViewField(ref.viewPager(), "pager");
		codeModelHelper.doViewById(afterViewsBody, "pager", pagerField);

		// configureViewPager();
		afterViewsBody.invoke("configureViewPager");

		// private void configureViewPager() {
		JMethod configureViewPager = jClass.method(JMod.PRIVATE, jCodeModel.VOID, "configureViewPager");
		JBlock configureViewPagerBody = configureViewPager.body();

		JClass viewPagerAdapterClass = ref.ref(application.getViewPagerAdapterPackage());

		// ViewFragmentPagerAdapter viewPagerAdapter = new ViewFragmentPagerAdapter(getSupportFragmentManager(), locations);
		JInvocation getFragmentManagerInvoke = JExpr.invoke("getSupportFragmentManager");
		JInvocation newViewPagerAdapter = JExpr._new(viewPagerAdapterClass).arg(getFragmentManagerInvoke);
		if (state.isListNavigation() || state.isTabNavigation()) {
			newViewPagerAdapter.arg(locationsField);
		}
		JVar viewPagerAdapterVar = configureViewPagerBody.decl(viewPagerAdapterClass, "viewPagerAdapter", newViewPagerAdapter);

		// pager.setAdapter(viewPagerAdapter);
		configureViewPagerBody.invoke(pagerField, "setAdapter").arg(viewPagerAdapterVar);

		if (state.isListNavigation() || state.isTabNavigation()) {
			// pager.setOnPageChangeListener(this);
			configureViewPagerBody.invoke(pagerField, "setOnPageChangeListener").arg(JExpr._this());

			// implements OnPageChangeListener
			jClass._implements(ref.onPageChangeListener());

			jClass.direct("@Override\n" + //
					"public void onPageScrollStateChanged(int position) {}");

			jClass.direct("@Override\n" + //
					" public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}");

			// @Override
			// public void onPageSelected(int position) {
			JMethod onPageSelectedMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onPageSelected");
			JVar positionVar = onPageSelectedMethod.param(jCodeModel.INT, "position");
			JBlock onPageSelectedBody = onPageSelectedMethod.body();
			if (state.isTabNavigation()) {
				// Tab tab = getSupportActionBar().getTabAt(position);
				// getSupportActionBar().selectTab(tab);
				JInvocation getTabAtMethod = JExpr.invoke("getSupportActionBar").invoke("getTabAt").arg(positionVar);
				JVar tabVar = onPageSelectedBody.decl(ref.sTab(), "tab", getTabAtMethod);
				JInvocation getSupportActionBarInvoke = JExpr.invoke("getSupportActionBar");
				onPageSelectedBody.invoke(getSupportActionBarInvoke, "selectTab").arg(tabVar);

			} else if (state.isListNavigation()) {
				// getSupportActionBar().setSelectedNavigationItem(position);
				JInvocation getSupportActionBarInvoke = JExpr.invoke("getSupportActionBar");
				onPageSelectedBody.invoke(getSupportActionBarInvoke, "setSelectedNavigationItem").arg(positionVar);
			}
		}
	}

}

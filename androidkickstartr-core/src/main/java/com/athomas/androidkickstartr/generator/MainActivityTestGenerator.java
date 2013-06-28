package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.AppDetails;
import com.athomas.androidkickstartr.util.CodeModelHelper;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class MainActivityTestGenerator implements Generator {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	private JDefinedClass jClass;
	private RefHelper ref;
	private JCodeModel jCodeModel;
	private AppDetails appDetails;

	private CodeModelHelper codeModelHelper;

	public MainActivityTestGenerator(AppDetails appDetails) {
		this.appDetails = appDetails;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		this.jCodeModel = jCodeModel;
		this.ref = ref;
		codeModelHelper = new CodeModelHelper(ref, appDetails);

		startGeneration(jCodeModel);
		return jCodeModel;
	}

	private void startGeneration(JCodeModel jCodeModel) {
		try {
			jClass = jCodeModel._class(appDetails.getActivityTestPackage());

			createTestActivity();

			String activityPackage = appDetails.getActivityPackage();
			if (appDetails.isAndroidAnnotations()) {
				activityPackage = activityPackage + "_";
			}
			JFieldVar activity = jClass.field(JMod.PRIVATE, ref.ref(activityPackage), "activityTest");

			JBlock createSetUpMethod = createSetUpMethod(activity);

			createTestAppName(activity);

			if (appDetails.isSample()) {
				JFieldVar component = null;
				if (appDetails.isViewPager()) {
					component = jClass.field(JMod.PRIVATE, ref.viewPager(), "pager");
					codeModelHelper.doFindViewById(createSetUpMethod, "pager", component, activity);
				} else {
					component = jClass.field(JMod.PRIVATE, ref.textView(), "hello");
					codeModelHelper.doFindViewById(createSetUpMethod, "hello", component, activity);
				}

				// Create test methods
				if (appDetails.isViewPager()) {
					createTestPagerNotNull(component);
				} else {
					createTestContentTextView(component);
				}
			}

		} catch (JClassAlreadyExistsException e1) {
			LOGGER.error("Classname already exists", e1);
		}
	}

	private void createTestActivity() {

		// @RunWith
		JAnnotationUse runWithAnnotation = jClass.annotate(ref.runWith());
		JExpression field = null;
		if (appDetails.isActionBarSherlock() && !appDetails.isEclipse()) {
			field = ref.customTestRunner(appDetails).dotclass();
		} else {
			field = ref.robolectricTestRunner().dotclass();
		}
		runWithAnnotation.param("value", field);
	}

	private JBlock createSetUpMethod(JFieldVar activity) {
		// Method setUp
		JMethod setUp = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "setUp");
		setUp.annotate(ref.before());
		setUp._throws(Exception.class);

		JBlock setUpBody = setUp.body();

		if (appDetails.isActionBarSherlock()) {
			setUpBody.staticInvoke(ref.sActionBarSherlock(), "registerImplementation").arg(ref.mockActionBarSherlock(appDetails.getPackageName()).dotclass());
			setUpBody.staticInvoke(ref.sActionBarSherlock(), "unregisterImplementation").arg(ref.sActionBarSherlockCompat().dotclass());
			setUpBody.staticInvoke(ref.sActionBarSherlock(), "unregisterImplementation").arg(ref.sActionBarSherlockNative().dotclass());
		}

		// declare tested activity and create it
		setUpBody.assign(activity, ref.robolectric().staticInvoke("buildActivity").arg(ref.activity(appDetails, appDetails.isAndroidAnnotations()).dotclass()).invoke("create").invoke("get"));

		return setUpBody;
	}

	private void createTestPagerNotNull(JFieldVar pager) {
		JMethod testContentTextView = createTestMethod("testPagerNotNull");
		JBlock testContentTextViewBody = testContentTextView.body();
		testContentTextViewBody.staticInvoke(ref.assertJunit(), "assertNotNull").arg(pager);
	}

	private void createTestAppName(JFieldVar activity) {
		JMethod testAppName = createTestMethod("testAppName");
		JBlock testAppNameBody = testAppName.body();
		JVar appName = testAppNameBody.decl(ref.string(), "appName", activity.invoke("getResources").invoke("getString").arg(ref.r().staticRef("string").ref("app_name")));
		testAppNameBody.staticInvoke(ref.assertJunit(), "assertEquals").arg(appName).arg(appDetails.getName());
	}

	private void createTestContentTextView(JFieldVar textView) {
		JMethod testContentTextView = createTestMethod("testContentTextView");
		JBlock testContentTextViewBody = testContentTextView.body();
		JVar textContent = testContentTextViewBody.decl(ref.string(), "textContent", textView.invoke("getText").invoke("toString"));
		testContentTextViewBody.staticInvoke(ref.assertJunit(), "assertEquals").arg(textContent).arg("Hello world!");
	}

	private JMethod createTestMethod(String methodName) {
		JMethod testMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, methodName);
		testMethod.annotate(ref.test());
		testMethod._throws(Exception.class);
		return testMethod;
	}

}

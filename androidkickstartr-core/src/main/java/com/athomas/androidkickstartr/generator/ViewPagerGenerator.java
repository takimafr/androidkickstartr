package com.athomas.androidkickstartr.generator;

import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.CodeModelUtils;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class ViewPagerGenerator implements Generator {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private RefHelper ref;
	private JDefinedClass jClass;
	private Application application;
	private final State state;
	private JCodeModel jCodeModel;

	public ViewPagerGenerator(State state, Application application) {
		this.state = state;
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException {
		this.jCodeModel = jCodeModel;
		ref = new RefHelper(jCodeModel);

		try {
			jClass = jCodeModel._class(application.getViewPagerAdapterPackage());

			// TODO Enhance that
			ref.r(application.getR()); // must do it at least once

			createViewPagerAdapter();

		} catch (JClassAlreadyExistsException e1) {
			LOGGER.error("Classname already exists", e1);
		}
		return jCodeModel;

	}

	private void createViewPagerAdapter() {
		jClass._extends(ref.pagerAdapter());

		// private Context context;
		JFieldVar contextField = jClass.field(PRIVATE, ref.context(), "context");

		// private final String[] locations;
		JFieldVar locationsField = null;
		if (state.isActionBarSherlock() && (state.isListNavigation() || state.isTabNavigation())) {
			locationsField = jClass.field(PRIVATE, ref.string().array(), "locations");
		}

		// public ViewPagerAdapter(Context context, String[] locations) {
		// this.context = context;
		// this.locations = locations; 
		// }
		CodeModelUtils.publicConstructor(jClass, contextField, locationsField);

		// @Override
		// public int getCount() { return locations.length; }
		JMethod getCountMethod = jClass.method(PUBLIC, jCodeModel.INT, "getCount");
		getCountMethod.annotate(ref.override());

		if (state.isActionBarSherlock() && (state.isListNavigation() || state.isTabNavigation())) {
			getCountMethod.body()._return(locationsField.ref("length"));
		} else {
			getCountMethod.body().directStatement("return 3;");
		}

		// @Override
		// public Object instantiateItem(ViewGroup container, int position) {
		JMethod instantiateItemMethod = jClass.method(JMod.PUBLIC, jCodeModel.ref(Object.class), "instantiateItem");
		instantiateItemMethod.annotate(ref.override());
		JVar containerParam = instantiateItemMethod.param(ref.viewGroup(), "container");
		instantiateItemMethod.param(jCodeModel.INT, "position");

		JBlock instantiateItemMethodBody = instantiateItemMethod.body();

		JInvocation newTextView = JExpr._new(ref.textView()).arg(contextField);
		JVar textViewVar = instantiateItemMethodBody.decl(ref.textView(), "textView", newTextView);

		if (state.isActionBarSherlock() && (state.isListNavigation() || state.isTabNavigation())) {
			// textView.setText(locations[position]);
			instantiateItemMethodBody.directStatement("textView.setText(locations[position]);");
		} else {
			instantiateItemMethodBody.directStatement("textView.setText(\"TEXTVIEW \" + position);");
		}

		// textView.setTextColor(Color.GRAY);
		instantiateItemMethodBody.invoke(textViewVar, "setTextColor").arg(ref.color().staticRef("GRAY"));
		
		// textView.setGravity(Gravity.CENTER);
		instantiateItemMethodBody.invoke(textViewVar, "setGravity").arg(ref.gravity().staticRef("CENTER"));
		
		
		// ((ViewPager) container).addView(textView);
		instantiateItemMethodBody.invoke(((JExpression) JExpr.cast(ref.viewPager(), containerParam)), "addView").arg(textViewVar);
		
		// return textView;
		instantiateItemMethodBody._return(textViewVar);

		// @Override
		// public boolean isViewFromObject(View view, Object object) {
		JMethod isViewFromObjectMethod = jClass.method(JMod.PUBLIC, jCodeModel.BOOLEAN, "isViewFromObject");
		isViewFromObjectMethod.annotate(ref.override());
		isViewFromObjectMethod.param(ref.view(), "view");
		isViewFromObjectMethod.param(Object.class, "object");
		// return view == ((TextView) object);
		JBlock isViewFromObjectMethodBody = isViewFromObjectMethod.body();
		isViewFromObjectMethodBody.directStatement("return view == ((TextView) object);");

		// @Override
		// public void destroyItem(ViewGroup container, int position, Object object) {
		JMethod destroyItemMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "destroyItem");
		destroyItemMethod.annotate(ref.override());
		destroyItemMethod.param(ref.viewGroup(), "container");
		destroyItemMethod.param(jCodeModel.INT, "position");
		destroyItemMethod.param(Object.class, "object");
		JBlock destroyItemMethodBody = destroyItemMethod.body();
		// ((ViewPager) container).removeView((TextView) object);
		destroyItemMethodBody.directStatement("((ViewPager) container).removeView((TextView) object);");

	}
}

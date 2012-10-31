package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class ViewPagerAdapterGenerator implements Generator {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private RefHelper ref;
	private JDefinedClass jClass;
	private Application application;
	private final State state;

	public ViewPagerAdapterGenerator(State state, Application application) {
		this.state = state;
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException {
		ref = new RefHelper(jCodeModel);

		try {
			jClass = jCodeModel._class(application.getViewPagerAdapterPackage());

			// TODO Enhance that
			ref.r(application.getR()); // must do it at least once

			// public class ViewFragmentPagerAdapter extends FragmentPagerAdapter {
			jClass._extends(ref.fragmentPagerAdapter());

			boolean hasLocationsField = state.isTabNavigation() || state.isListNavigation();

			JFieldVar locationsField = null;
			if (hasLocationsField) {
				// private String[] locations;
				locationsField = jClass.field(JMod.PRIVATE, ref.string().array(), "locations");
			}

			// public ViewFragmentPagerAdapter(FragmentManager fm, String[] locations) {
			JMethod constructor = jClass.constructor(JMod.PUBLIC);
			constructor.param(ref.fragmentManager(), "fm");
			if (hasLocationsField) {
				constructor.param(ref.string().array(), "locations");
			}

			JBlock constructorBody = constructor.body();

			// super(fm);
			constructorBody.directStatement("super(fm);");

			if (hasLocationsField) {
				// this.locations = locations;
				constructorBody.assign(JExpr._this().ref("locations"), locationsField);
			}

			// @Override
			// public int getCount() {
			JMethod getCountMethod = jClass.method(JMod.PUBLIC, jCodeModel.INT, "getCount");
			JBlock getCountMethodBody = getCountMethod.body();
			if (hasLocationsField) {
				//  return locations.length;
				getCountMethodBody._return(locationsField.ref("length"));
			} else {
				// return 3;
				getCountMethodBody.directStatement("return 3;");
			}

			// @Override
			// public Fragment getItem(int position) {
			JMethod getItemMethod = jClass.method(JMod.PUBLIC, ref.fragment(), "getItem");
			getItemMethod.param(jCodeModel.INT, "position");
			JBlock getItemMethodBody = getItemMethod.body();

			// Fragment fragment = new SampleFragment();
			String sampleFragmentPackage = application.getSampleFragmentPackage();
			if (state.isAndroidAnnotations()) {
				sampleFragmentPackage += "_";
			}

			JVar fragmentVar = getItemMethodBody.decl(ref.fragment(), "fragment", JExpr._new(ref.ref(sampleFragmentPackage)));

			// Bundle bundle = new Bundle();
			JVar bundleVar = getItemMethodBody.decl(ref.bundle(), "bundle", JExpr._new(ref.bundle()));

			if (hasLocationsField) {
				// bundle.putString("label", locations[position]);
				getItemMethodBody.directStatement("bundle.putString(\"label\", locations[position]);");
			} else {
				// bundle.putString("label", "LABEL " + position);
				getItemMethodBody.directStatement("bundle.putString(\"label\", \"LABEL \" + position);");
			}

			// fragment.setArguments(bundle);
			getItemMethodBody.invoke(fragmentVar, "setArguments").arg(bundleVar);

			// return fragment;
			getItemMethodBody._return(fragmentVar);

		} catch (JClassAlreadyExistsException e1) {
			LOGGER.error("Classname already exists", e1);
		}
		return jCodeModel;

	}

}

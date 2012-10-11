package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

public class ViewPagerGenerator implements Generator {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private RefHelper ref;
	private JDefinedClass jClass;
	private Application application;

	public ViewPagerGenerator(Application application) {
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException {
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
		jClass._extends(ref.fragmentPagerAdapter());
	}

}

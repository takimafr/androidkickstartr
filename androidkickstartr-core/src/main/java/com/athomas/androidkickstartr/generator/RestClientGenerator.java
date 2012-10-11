package com.athomas.androidkickstartr.generator;

import static com.sun.codemodel.ClassType.INTERFACE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

/**
 * Generates a RestClient interface annotated with @Rest annotation.
 * 
 * @author Alexandre THOMAS
 * 
 */
public class RestClientGenerator implements Generator {

	private Logger logger;
	private RefHelper ref;
	private JDefinedClass jClass;
	private Application application;

	public RestClientGenerator(Application application) {
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel) throws IOException {
		logger = LoggerFactory.getLogger(getClass());

		ref = new RefHelper(jCodeModel);

		try {
			ref.r(application.getR()); // must do it at least once

			jClass = jCodeModel._class(application.getRestClientPackage(), INTERFACE);

			JAnnotationUse rest = jClass.annotate(ref.rest());
			rest.param("value", application.getRestTemplateUrl());

			// TODO search the JMod for interface method
			JMethod mainMethod = jClass.method(JMod.ABSTRACT | JMod.PUBLIC, jCodeModel.VOID, "main");
			JAnnotationUse get = mainMethod.annotate(ref.get());
			get.param("value", "/");

		} catch (JClassAlreadyExistsException e1) {
			logger.error("Classname already exists", e1);
		}
		return jCodeModel;
	}
}

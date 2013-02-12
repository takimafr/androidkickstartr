package com.athomas.androidkickstartr.generator;

import static com.sun.codemodel.ClassType.INTERFACE;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.AppDetails;
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
	private JDefinedClass jClass;
	private AppDetails appDetails;

	public RestClientGenerator(AppDetails appDetails) {
		this.appDetails = appDetails;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		logger = LoggerFactory.getLogger(getClass());

		try {
			jClass = jCodeModel._class(appDetails.getRestClientPackage(), INTERFACE);

			/*
			 * @Rest( rootUrl = "<ROOT_URL>", converters =
			 * StringHttpMessageConverter.class)
			 */
			JAnnotationUse rest = jClass.annotate(ref.rest());
			rest.param("rootUrl", appDetails.getRestTemplateUrl());
			rest.param("converters", ref.stringHttpMessageConverter().dotclass());

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

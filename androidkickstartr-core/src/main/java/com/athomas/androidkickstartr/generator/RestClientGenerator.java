/**
 * Copyright (C) 2012-2013 eBusiness Information, Excilys Group (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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

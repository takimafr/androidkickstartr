/**
 * Copyright (C) 2012-2013 eBusiness Information (www.ebusinessinformation.fr)
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

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.AppDetails;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;

public class ApplicationGenerator implements Generator {

	private Logger logger;
	private JDefinedClass jClass;
	private AppDetails appDetails;

	public ApplicationGenerator(AppDetails appDetails) {
		this.appDetails = appDetails;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		logger = LoggerFactory.getLogger(getClass());
		try {
			jClass = jCodeModel._class(appDetails.getApplicationPackage());

			jClass._extends(ref.application());

			jClass.annotate(ref.reportsCrashes()).param("formKey", "YOUR_FORM_KEY");

			JMethod onCreateMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onCreate");
			onCreateMethod.annotate(ref.override());

			JBlock onCreateMethodBody = onCreateMethod.body();

			onCreateMethodBody.staticInvoke(ref.acra(), "init").arg(JExpr._this());

			onCreateMethodBody.invoke(JExpr._super(), "onCreate");

		} catch (JClassAlreadyExistsException e1) {
			logger.error("Classname already exists", e1);
		}
		return jCodeModel;

	}

}

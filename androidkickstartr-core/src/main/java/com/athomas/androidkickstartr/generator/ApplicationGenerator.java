package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
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
	private Application application;

	public ApplicationGenerator(Application application) {
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		logger = LoggerFactory.getLogger(getClass());
		try {
			jClass = jCodeModel._class(application.getApplicationPackage());

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

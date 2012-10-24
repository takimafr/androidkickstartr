package com.athomas.androidkickstartr.util;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;

/**
 * Useful utils for code model
 * 
 * @author Alexandre THOMAS
 */
public class CodeModelUtils {

	public static void publicConstructor(JDefinedClass jClass, JFieldVar... fields) {
		constructor(JMod.PUBLIC, jClass, fields);
	}

	public static void constructor(int mod, JDefinedClass jClass, JFieldVar... fields) {
		JMethod constructor = jClass.constructor(mod);
		JBlock constructorBody = constructor.body();

		for (JFieldVar field : fields) {
			if (field != null) {
				JVar param = constructor.param(field.type(), field.name());
				constructorBody.assign(JExpr._this().ref(field), param);
			}
		}
	}

	public static JFieldVar privateConstant(JDefinedClass jClass, JType type, String name) {
		return constant(JMod.PRIVATE, jClass, type, name);
	}

	public static JFieldVar publicConstant(JDefinedClass jClass, JType type, String name) {
		return constant(JMod.PUBLIC, jClass, type, name);
	}

	public static JFieldVar constant(int mod, JDefinedClass jClass, JType type, String name) {
		return jClass.field(mod | JMod.STATIC | JMod.FINAL, type, name);
	}

}

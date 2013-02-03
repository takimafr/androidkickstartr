package com.athomas.androidkickstartr.util;

import com.athomas.androidkickstartr.model.State;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JVar;

public class CodeModelHelper {

	private RefHelper ref;
	private State state;

	public CodeModelHelper(RefHelper ref, State state) {
		this.ref = ref;
		this.state = state;
	}

	public void doViewById(JBlock body, String id, JFieldVar field) {
		if (state.isAndroidAnnotations()) {
			field.annotate(ref.viewById());
		} else if (state.isRoboguice()) {
			JAnnotationUse injectViewAnnotation = field.annotate(ref.injectView());
			JFieldRef rId = ref.r().staticRef("id").ref(id);
			injectViewAnnotation.param("value", rId);
		} else {
			doFindViewById(body, id, field);
		}
	}

	private void doFindViewById(JBlock afterViewsBody, String id, JFieldVar field) {
		doFindViewById(afterViewsBody, id, field, null);
	}

	public void doFindViewById(JBlock afterViewsBody, String id, JFieldVar field, JVar contentViewVar) {
		JFieldRef rId = ref.r().staticRef("id").ref(id);
		JInvocation findViewById = JExpr.invoke(contentViewVar, "findViewById").arg(rId);
		JExpression findViewByIdCasted = JExpr.cast(field.type(), findViewById);
		afterViewsBody.assign(field, findViewByIdCasted);
	}

}

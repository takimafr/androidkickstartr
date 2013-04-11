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
package com.athomas.androidkickstartr.util;

import com.athomas.androidkickstartr.AppDetails;
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
	private AppDetails state;

	public CodeModelHelper(RefHelper ref, AppDetails state) {
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

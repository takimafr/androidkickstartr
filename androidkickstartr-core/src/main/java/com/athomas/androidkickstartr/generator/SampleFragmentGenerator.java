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
import com.athomas.androidkickstartr.util.CodeModelHelper;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class SampleFragmentGenerator implements Generator {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private RefHelper ref;
	private JDefinedClass jClass;
	private final AppDetails appDetails;
	private CodeModelHelper codeModelHelper;

	public SampleFragmentGenerator(AppDetails state) {
		this.appDetails = state;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		this.ref = ref;
		codeModelHelper = new CodeModelHelper(ref, appDetails);

		try {
			jClass = jCodeModel._class(appDetails.getSampleFragmentPackage());

			if (appDetails.isRoboguice() && appDetails.isActionBarSherlock()) {
				// public class SampleFragment extends RoboSherlockFragment {
				jClass._extends(ref.ref(appDetails.getRoboSherlockFragmentPackage()));
			} else if (appDetails.isRoboguice()) {
				// public class SampleFragment extends RoboFragment {
				jClass._extends(ref.roboFragment());
			} else {
				// public class SampleFragment extends Fragment {
				jClass._extends(ref.fragment());
			}

			// private TextView labelText;
			JFieldVar labelTextField = jClass.field(appDetails.isAndroidAnnotations() || appDetails.isRoboguice() ? JMod.NONE : JMod.PRIVATE, ref.textView(), "labelText");

			JBlock onCreateViewMethodBody;
			if (appDetails.isAndroidAnnotations()) {
				// @EFragment(R.layout.fragment_sample)
				jClass.annotate(ref.efragment()).param("value", ref.r().staticRef("layout").ref("fragment_sample"));

				// @ViewById
				labelTextField.annotate(ref.viewById());

				// @AfterViews
				// void afterViews() {
				JMethod onCreateViewMethod = jClass.method(JMod.NONE, jCodeModel.VOID, "afterViews");
				onCreateViewMethod.annotate(ref.afterViews());
				onCreateViewMethodBody = onCreateViewMethod.body();

				extractArguments(labelTextField, onCreateViewMethodBody);
				
			} else if (appDetails.isRoboguice()) {
				// @InjectView(R.id.label_text)
				JAnnotationUse injectViewAnnotation = labelTextField.annotate(ref.injectView());
				JFieldRef field = ref.r().staticRef("id").ref("label_text");
				injectViewAnnotation.param("value", field);
				
				// @Override
				// public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				JMethod onCreateViewMethod = jClass.method(JMod.PUBLIC, ref.view(), "onCreateView");
				onCreateViewMethod.annotate(ref.override());
				JVar inflaterParam = onCreateViewMethod.param(ref.layoutInflater(), "inflater");
				JVar containerParam = onCreateViewMethod.param(ref.viewGroup(), "container");
				onCreateViewMethod.param(ref.bundle(), "savedInstanceState");
				onCreateViewMethodBody = onCreateViewMethod.body();
				
				onCreateViewMethodBody._return(inflateView(inflaterParam, containerParam));
				
				// @Override
			    // public void onViewCreated(View view, Bundle savedInstanceState) {
				JMethod onViewCreatedMethod = jClass.method(JMod.PUBLIC, jCodeModel.VOID, "onViewCreated");
				onViewCreatedMethod.annotate(ref.override());
				JVar viewParam = onViewCreatedMethod.param(ref.view(), "view");
				JVar savedInstanceStateParam = onViewCreatedMethod.param(ref.bundle(), "savedInstanceState");
				JBlock onViewCreatedMethodBody = onViewCreatedMethod.body();
				
				// super.onViewCreated(view, savedInstanceState)
				onViewCreatedMethodBody.invoke(JExpr._super(), "onViewCreated").arg(viewParam).arg(savedInstanceStateParam);
				
				extractArguments(labelTextField, onViewCreatedMethodBody);
			} else {
				// @Override
				// public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				JMethod onCreateViewMethod = jClass.method(JMod.PUBLIC, ref.view(), "onCreateView");
				onCreateViewMethod.annotate(ref.override());
				JVar inflaterParam = onCreateViewMethod.param(ref.layoutInflater(), "inflater");
				JVar containerParam = onCreateViewMethod.param(ref.viewGroup(), "container");
				onCreateViewMethod.param(ref.bundle(), "savedInstanceState");
				onCreateViewMethodBody = onCreateViewMethod.body();

				// View contentView = inflater.inflate(R.layout.fragment_sample, container, false);
				JInvocation inflateInvoke = inflateView(inflaterParam, containerParam);
				JVar contentViewVar = onCreateViewMethodBody.decl(ref.view(), "contentView", inflateInvoke);

				// labelText = (TextView) contentView.findViewById(R.id.label);
				codeModelHelper.doFindViewById(onCreateViewMethodBody, "label_text", labelTextField, contentViewVar);

				extractArguments(labelTextField, onCreateViewMethodBody);

				// return contentView;
				onCreateViewMethodBody._return(contentViewVar);
			}

		} catch (JClassAlreadyExistsException e) {
			LOGGER.error("Classname already exists", e);
		}
		return jCodeModel;

	}

	private void extractArguments(JFieldVar labelTextField, JBlock methodBody) {
		// Bundle bundle = getArguments();
		JVar bundleVar = methodBody.decl(ref.bundle(), "bundle", JExpr.invoke("getArguments"));

		// String label = bundle.getString("label");
		JVar labelVar = methodBody.decl(ref.string(), "label", bundleVar.invoke("getString").arg("label"));

		// labelText.setText(label);
		methodBody.invoke(labelTextField, "setText").arg(labelVar);
	}
	
	private JInvocation inflateView(JVar inflaterParam, JVar containerParam) {
		// inflater.inflate(R.layout.fragment_sample, container, false);
		return JExpr.invoke(inflaterParam, "inflate").arg(ref.r().staticRef("layout").ref("fragment_sample")).//
				arg(containerParam). //
				arg(JExpr.FALSE);
	}

}

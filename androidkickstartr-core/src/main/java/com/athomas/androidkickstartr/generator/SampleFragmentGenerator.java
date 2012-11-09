package com.athomas.androidkickstartr.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athomas.androidkickstartr.model.Application;
import com.athomas.androidkickstartr.model.State;
import com.athomas.androidkickstartr.util.CodeModelHelper;
import com.athomas.androidkickstartr.util.RefHelper;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class SampleFragmentGenerator implements Generator {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private RefHelper ref;
	private JDefinedClass jClass;
	private Application application;
	private final State state;
	private CodeModelHelper codeModelHelper;

	public SampleFragmentGenerator(State state, Application application) {
		this.state = state;
		this.application = application;
	}

	public JCodeModel generate(JCodeModel jCodeModel, RefHelper ref) throws IOException {
		ref = this.ref;
		codeModelHelper = new CodeModelHelper(ref, state);

		try {
			jClass = jCodeModel._class(application.getSampleFragmentPackage());

			// public class SampleFragment extends Fragment {
			jClass._extends(ref.fragment());

			// private TextView labelText;
			JFieldVar labelTextField = jClass.field(state.isAndroidAnnotations() ? JMod.NONE : JMod.PRIVATE, ref.textView(), "labelText");

			JBlock onCreateViewMethodBody;
			if (state.isAndroidAnnotations()) {
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
				JInvocation inflateInvoke = JExpr.invoke(inflaterParam, "inflate").arg(ref.r().staticRef("layout").ref("fragment_sample")).//
						arg(containerParam). //
						arg(JExpr.FALSE);
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

	private void extractArguments(JFieldVar labelTextField, JBlock onCreateViewMethodBody) {
		// Bundle bundle = getArguments();
		JVar bundleVar = onCreateViewMethodBody.decl(ref.bundle(), "bundle", JExpr.invoke("getArguments"));

		// String label = bundle.getString("label");
		JVar labelVar = onCreateViewMethodBody.decl(ref.string(), "label", bundleVar.invoke("getString").arg("label"));

		// labelText.setText(label);
		onCreateViewMethodBody.invoke(labelTextField, "setText").arg(labelVar);
	}

}

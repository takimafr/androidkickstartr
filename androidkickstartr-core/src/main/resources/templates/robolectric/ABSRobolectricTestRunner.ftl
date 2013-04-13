package ${application.packageName}.test;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ActionBarSherlockNative;
import ${application.packageName}.test.mock.MockActionBarSherlock;

public class ABSRobolectricTestRunner extends RobolectricTestRunner {

	public ABSRobolectricTestRunner(Class<?> testClass) throws InitializationError {
		super(testClass);

		ActionBarSherlock.registerImplementation(MockActionBarSherlock.class);
		ActionBarSherlock.unregisterImplementation(ActionBarSherlockNative.class);
		ActionBarSherlock.unregisterImplementation(ActionBarSherlockCompat.class);
	}

	@Override
	protected void bindShadowClasses() {
		super.bindShadowClasses();
	}

}
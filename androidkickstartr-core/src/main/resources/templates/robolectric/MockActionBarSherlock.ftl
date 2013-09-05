package ${application.packageName}.absmock;

import org.robolectric.Robolectric;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.internal.ActionBarSherlockCompat;
import com.actionbarsherlock.internal.ActionBarSherlockNative;
import com.actionbarsherlock.view.MenuInflater;

@ActionBarSherlock.Implementation(api = 0)
public class MockActionBarSherlock extends ActionBarSherlockCompat {
	final private ActionBar actionBar;

	public MockActionBarSherlock(Activity activity, int flags) {
		super(activity, flags);
		actionBar = new MockActionBar(activity);
	}

	@Override
	public void setContentView(int layoutResId) {
		LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
		View contentView = layoutInflater.inflate(layoutResId, null);

		Robolectric.shadowOf(mActivity).setContentView(contentView);
	}

	@Override
	public void setContentView(View view) {
		Robolectric.shadowOf(mActivity).setContentView(view);
	}

	@Override
	public ActionBar getActionBar() {
		return actionBar;
	}

	@Override
	protected Context getThemedContext() {
		return mActivity;
	}

	@Override
	public MenuInflater getMenuInflater() {
		if (mMenuInflater == null) {
			mMenuInflater = new MockSherlockMenuInflater(mActivity);
		}
		return mMenuInflater;
	}
}
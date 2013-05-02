package ${application.packageName}.test.mock;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;

public class MockTab extends Tab {

	@Override
	public int getPosition() {
		return 0;
	}

	@Override
	public Drawable getIcon() {
		return null;
	}

	@Override
	public CharSequence getText() {
		return null;
	}

	@Override
	public Tab setIcon(Drawable icon) {
		return null;
	}

	@Override
	public Tab setIcon(int resId) {
		return null;
	}

	@Override
	public Tab setText(CharSequence text) {
		return null;
	}

	@Override
	public Tab setText(int resId) {
		return null;
	}

	@Override
	public Tab setCustomView(View view) {
		return null;
	}

	@Override
	public Tab setCustomView(int layoutResId) {
		return null;
	}

	@Override
	public View getCustomView() {
		return null;
	}

	@Override
	public Tab setTag(Object obj) {
		return null;
	}

	@Override
	public Object getTag() {
		return null;
	}

	@Override
	public Tab setTabListener(TabListener listener) {
		return null;
	}

	@Override
	public void select() {
	}

	@Override
	public Tab setContentDescription(int resId) {
		return null;
	}

	@Override
	public Tab setContentDescription(CharSequence contentDesc) {
		return null;
	}

	@Override
	public CharSequence getContentDescription() {
		return null;
	}

}
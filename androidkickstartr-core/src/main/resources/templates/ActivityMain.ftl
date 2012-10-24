<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent" >

	<#if State.viewPager>
	<android.support.v4.view.ViewPager
		android:id="@+id/pager"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:gravity="center" />
	<#else>
	<TextView
		android:id="@+id/hello"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_centerInParent="true"
		android:text="@string/hello_world" />
	</#if>

</RelativeLayout>
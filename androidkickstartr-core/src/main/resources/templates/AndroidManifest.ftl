<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="${Application.packageName}"
	android:versionCode="1"
	android:versionName="1.0" >

	<uses-sdk
		android:minSdkVersion="${Application.minSdk}"
		android:targetSdkVersion="${Application.targetSdk}" />
	
	<#list Application.permissions as permission>
		<uses-permission android:name="${permission}" />
	</#list>
		

	<application
		android:icon="@drawable/ic_launcher"
        android:theme="@style/AppTheme" 
		<#if State.acra>
		android:name="${ApplicationClassName}"
		</#if>
		android:label="@string/app_name" >
		<activity
			android:name="${Application.activity}"
			android:label="@string/app_name" >
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				<category android:name="android.intent.category.LAUNCHER" />
			</intent-filter>
		</activity>
	</application>

</manifest>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	package="${application.packageName}"
	android:versionCode="1"
	android:versionName="1.0" >

	<uses-sdk
		android:minSdkVersion="${application.minSdk}"
		android:targetSdkVersion="${application.targetSdk}" />
	
	<#list application.permissions as permission>
		<uses-permission android:name="${permission}" />
	</#list>
		

	<application
		android:icon="@drawable/ic_launcher"
        android:theme="@style/AppTheme"
        <#if !application.sample>
		<#if application.acra>
		android:name="${applicationClassName}"
		</#if>
		</#if>
		android:label="@string/app_name" >
		<activity
			android:name="${application.activity}"
			android:label="@string/app_name" >
			<intent-filter>
				<action android:name="android.intent.action.MAIN" />
				<category android:name="android.intent.category.LAUNCHER" />
			</intent-filter>
		</activity>
	</application>

</manifest>
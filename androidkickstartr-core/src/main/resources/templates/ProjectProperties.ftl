target=android-16
<#if application.actionBarSherlock>
android.library.reference.1=../actionbarsherlock
</#if>
<#if application.proguard>
proguard.config=proguard.cfg
</#if>
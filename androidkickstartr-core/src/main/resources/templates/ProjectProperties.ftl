target=android-${application.targetSdk}
<#if application.actionBarSherlock>
android.library.reference.1=../actionbarsherlock
</#if>
<#if application.proguard>
proguard.config=proguard.cfg
</#if>
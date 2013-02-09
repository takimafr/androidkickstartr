target=android-16
<#if State.actionBarSherlock>
android.library.reference.1=../actionbarsherlock
</#if>
<#if State.proguard>
proguard.config=proguard.cfg
</#if>
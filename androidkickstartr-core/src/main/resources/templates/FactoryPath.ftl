<factorypath>
	<#if application.maven>
    <factorypathentry kind="VARJAR" id="M2_REPO/com/googlecode/androidannotations/androidannotations/2.7/androidannotations-2.7.jar" enabled="true" runInBatchMode="false"/>
    <factorypathentry kind="VARJAR" id="M2_REPO/com/googlecode/androidannotations/androidannotations-api/2.7/androidannotations-api-2.7.jar" enabled="true" runInBatchMode="false"/>
    <factorypathentry kind="VARJAR" id="M2_REPO/com/sun/codemodel/codemodel/2.4.1/codemodel-2.4.1.jar" enabled="true" runInBatchMode="false"/>
    <#else>
    <factorypathentry kind="WKSPJAR" id="/${application.name}/compile-libs/androidannotations-2.7.jar" enabled="true" runInBatchMode="false"/>
    </#if>
</factorypath>

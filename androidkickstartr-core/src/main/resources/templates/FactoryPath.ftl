<factorypath>
	<#if State.maven>
    <factorypathentry kind="VARJAR" id="M2_REPO/com/googlecode/androidannotations/androidannotations/2.6/androidannotations-2.6.jar" enabled="true" runInBatchMode="false"/>
    <#else>
    <factorypathentry kind="WKSPJAR" id="/${Application.name}/ext-libs/androidannotations-2.6.jar" enabled="true" runInBatchMode="false"/>
    </#if>
</factorypath>

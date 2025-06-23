<#import "parts/common.ftlh" as c>
<#import "parts/logreg.ftlh" as l>

<@c.page>
  <#if message??>${message}</#if>
  <@l.login "/registration " true/>
</@c.page>

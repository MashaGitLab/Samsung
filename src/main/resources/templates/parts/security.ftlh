<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign user = Session.SPRING_SECURITY_CONTEXT.authentication.principal>
    <#if user?? && user.isAdmin??>
        <#assign name = user.getUsername()>
        <#assign isAdmin = user.isAdmin()>
        <#assign isUser = user.isUser()>
        <#assign isTeacher = user.isTeacher()>

    <#else>
        <#assign name = "unknown">
        <#assign isAdmin = false>
        <#assign isUser = false>
        <#assign isTeacher = false>

    </#if>
<#else>
    <#assign name = "unknown">
    <#assign isAdmin = false>
    <#assign isUser = false>
    <#assign isTeacher = false>
</#if>
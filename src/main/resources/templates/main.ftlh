<#import 'parts/common.ftlh' as c>

<@c.page>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Main Page</title>
</head>
<body>
<h1>Данные из базы</h1>
<a href="/decrypt">Расшифровать</a>
<table>
    <#list data as d>
    <tr>
        <th>ID</th>
        <th>Comment</th>
    </tr>
        <td>${d.id}</td>
        <td>${d.comment}</td>
    </tr>
    </#list>
</table>
</body>
</html>
</@c.page>

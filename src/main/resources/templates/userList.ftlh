<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
        .user-table {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            margin: 20px auto;
            padding: 20px;
            max-width: 1200px;
        }
        .user-table th {
            background-color: #f8f9fa;
        }
        body {
            background-image: url(static/background_main.jpg);
        }
        .decrypt-all-btn {
            margin-bottom: 20px;
        }
    </style>

    <div class="user-table">
        <h3 class="text-center mb-4">Список пользователей</h3>

        <!-- Кнопка для расшифровки всех пользователей -->
        <form action="/user/decrypt-all" method="post" class="text-center mb-4">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-info decrypt-all-btn">
                <i class="fas fa-lock-open"></i> Расшифровать все данные
            </button>
        </form>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Имя</th>
                <th>ФИО</th>
                <th>Номер карты</th>
                <th>Группа/Должность</th>
                <th>Роли</th>
                <th>Действия</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <#assign detail = details[user?index]>
                <#assign decrypted = decryptedData[user?index]>
                <tr>
                    <td>${user.username}</td>
                    <td>
                        <#if showDecrypted?? && showDecrypted && decrypted?exists && decrypted["fullName"]??>
                            ${decrypted["fullName"]}
                        <#else>
                            ${detail.fullName!''}
                        </#if>
                    </td>
                    <td>
                        <#if showDecrypted?? && showDecrypted && decrypted?exists && decrypted["cardNumber"]??>
                            ${decrypted["cardNumber"]}
                        <#else>
                            ${detail.cardNumber!''}
                        </#if>
                    </td>
                    <td>
                        <#if detail.groupName??>
                            ${detail.groupName}
                        <#elseif detail.position??>
                            ${detail.position}
                        </#if>
                    </td>
                    <td>
                        <#list user.roles as role>
                            <span class="badge bg-primary">${role}</span>
                        </#list>
                    </td>
                    <td>
                        <#if isAdmin?? && isAdmin> <!-- Добавлена проверка на существование -->

                        <a href="/user/${user.id}" class="btn btn-sm btn-warning">
                            <i class="fas fa-edit"></i>
                        </a>
                        <form action="/user/delete/${user.id}" method="post" class="d-inline">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-sm btn-danger">
                                <i class="fas fa-trash"></i>
                            </button>
                        </form>
                    </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>

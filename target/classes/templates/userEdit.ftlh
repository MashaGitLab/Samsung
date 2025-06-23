<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
        .edit-container {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            padding: 30px;
            margin: 50px auto;
            max-width: 800px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }

        body {
            background: url(static/background_main.jpg) no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .btn-action {
            min-width: 120px;
            margin: 0 10px;
        }
    </style>

    <div class="edit-container">
        <h2 class="text-center mb-4">Редактирование пользователя</h2>
        <form action="/user" method="post" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="username">Имя пользователя:</label>
                <input type="text"
                       class="form-control"
                       name="username"
                       value="${user.username}"
                       required
                       style="background-color: #f8f9fa;">
            </div>

            <div class="form-group mt-4">
                <label class="mb-3">Роли:</label>
                <div class="roles-container" style="column-count: 2;">
                    <#list roles as role>
                        <div class="form-check mb-2">
                            <input class="form-check-input"
                                   type="checkbox"
                                   name="${role}"
                                   id="${role}"
                                    ${user.roles?seq_contains(role)?then("checked", "")}>
                            <label class="form-check-label" for="${role}">
                                ${role}
                            </label>
                        </div>
                    </#list>
                </div>
            </div>

            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" name="_csrf" value="${_csrf.token}">

            <div class="text-center mt-5">
                <button type="submit" class="btn btn-success btn-action">
                    <i class="fas fa-save"></i> Сохранить
                </button>
                <a href="/user" class="btn btn-secondary btn-action">
                    <i class="fas fa-times"></i> Отмена
                </a>
            </div>
        </form>
    </div>
</@c.page>
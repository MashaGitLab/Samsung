<#macro login path isRegisterForm>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/static/style_logreg.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Login</title>
        <style>
            .field-hidden {
                display: none;
            }
        </style>
    </head>

    <body>
    <div class="container main">
        <div class="row">
            <div class="col-md-6 side-image">
                <!-- Изображение -->
            </div>

            <div class="col-md-6 right">
                <div class="input-box">
                    <form action="${path}" method="post">
                        <header>Создать аккаунт</header>

                        <!-- Основные поля -->
                        <div class="input-field">
                            <input type="text" name="username" class="input" id="username" required autocomplete="off">
                            <label for="username">Ник</label>
                        </div>
                        <div class="input-field">
                            <input type="password" name="password" class="input" id="password" required>
                            <label for="password">Пароль</label>
                        </div>

                        <!-- Выбор типа пользователя -->
                        <div class="input-field">
                            <select name="role" class="form-select" id="roleSelect" onchange="updateFields()">
                                <option value="1001">Студент</option>
                                <option value="1002">Преподаватель</option>
                                <option value="1003">Администратор</option>
                            </select>
                        </div>

                        <!-- Скрытое поле для secret -->
                        <input type="hidden" name="secret" id="secretInput" value="1001">

                        <!-- Дополнительные поля -->
                        <div class="input-field">
                            <input type="text" name="fullName" class="input" id="fullName" required>
                            <label for="fullName">ФИО</label>
                        </div>
                        <div class="input-field">
                            <input type="text" name="cardNumber" class="input" id="cardNumber" required>
                            <label for="cardNumber">Номер карты</label>
                        </div>

                        <!-- Группа (только для студентов) -->
                        <div class="input-field" id="groupField">
                            <input type="text" name="group" class="input" id="group">
                            <label for="group">Номер группы (необязательно)</label>
                        </div>

                        <!-- Должность (только для преподавателей) -->
                        <div class="input-field field-hidden" id="positionField">
                            <input type="text" name="position" class="input" id="position">
                            <label for="position">Должность</label>
                        </div>

                        <!-- CSRF и кнопка отправки -->
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <div class="input-field">
                            <input type="submit" class="submit" value="Создать">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function updateFields() {
            const roleSelect = document.getElementById('roleSelect');
            const secretInput = document.getElementById('secretInput');
            const groupField = document.getElementById('groupField');
            const positionField = document.getElementById('positionField');

            // Обновляем значение secret
            secretInput.value = roleSelect.value;

            // Управление видимостью полей
            switch(roleSelect.value) {
                case '1001': // Студент
                    groupField.classList.remove('field-hidden');
                    positionField.classList.add('field-hidden');
                    document.getElementById('group').required = false;
                    document.getElementById('position').required = false;
                    break;
                case '1002': // Преподаватель
                    groupField.classList.add('field-hidden');
                    positionField.classList.remove('field-hidden');
                    document.getElementById('position').required = true;
                    break;
                case '1003': // Администратор
                    groupField.classList.add('field-hidden');
                    positionField.classList.add('field-hidden');
                    break;
            }
        }

        // Инициализация при загрузке
        document.addEventListener('DOMContentLoaded', updateFields);
    </script>
    </body>
    </html>
</#macro>

<#macro logout>
    <div>
        <form action="/logout" method="post">
            <button class="btn btn-primary" type="submit">
                Покинуть
            </button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
</#macro>
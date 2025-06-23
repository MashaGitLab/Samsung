<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
        .log-table {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            margin: 20px auto;
            padding: 20px;
            max-width: 1200px;
        }
        .log-table th {
            background-color: #f8f9fa;
        }
        body {
            background-image: url(static/background_main.jpg);
        }
    </style>

    <div class="log-table">
        <h3 class="text-center mb-4">Журнал посещений</h3>

        <!-- Поле для поиска -->
        <form method="GET" action="/attendance">
            <div class="input-group mb-3">
                <input type="text" name="username" class="form-control" placeholder="Введите имя пользователя">
                <button class="btn btn-primary" type="submit">Найти</button>
            </div>
        </form>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>Пользователь</th>
                <th>Время события</th>
                <th>Тип события</th>
                <th>Аудитория</th>
            </tr>
            </thead>
            <tbody>
            <#list logs as log>
                <tr>
                    <td>${log.username!''}</td>
                    <td>${log.formattedEventTime}</td>
                    <td>
                        <#if log.eventType == 'ЗАШЕЛ'>
                            <span class="badge bg-success">Вход</span>
                        <#else>
                            <span class="badge bg-danger">Выход</span>
                        </#if>
                    </td>
                    <td>${log.roomNumber!''}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>

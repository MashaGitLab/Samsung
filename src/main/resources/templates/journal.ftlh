<#import "parts/common.ftlh" as c>
<@c.page>
    <style>
        .journal-table {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            margin: 20px auto;
            padding: 20px;
            max-width: 1200px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .journal-table th {
            background-color: #f8f9fa;
            padding: 12px;
            text-align: center;
        }

        .journal-table td {
            padding: 10px;
            vertical-align: middle;
        }

        .journal-table tr:nth-child(even) {
            background-color: rgba(0, 0, 0, 0.05);
        }

        .journal-table tr:hover {
            background-color: rgba(0, 0, 0, 0.1);
        }

        body {
            background-image: url('/static/PhotoForMain/background_main.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            font-family: Arial, sans-serif;
        }

        .search-container {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
        }

        .search-form {
            display: flex;
            gap: 10px;
        }

        .search-input {
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 300px;
        }

        .search-button {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .search-button:hover {
            background-color: #0056b3;
        }
    </style>

    <div class="journal-table">
        <h1>Журнал событий</h1>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Событие</th>
                <th>Время</th>
            </tr>
            </thead>
            <tbody>
            <#list records as journal>
                <tr>
                    <td>${journal.comment}</td>
                    <td>${journal.time}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>

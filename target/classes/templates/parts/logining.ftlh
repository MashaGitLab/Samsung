<#macro login path isRegisterForm>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/style_logreg.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Login</title>
</head>

<body>
<div class="wrapper">
    <div class="container main">
        <div class="row">
            <div class="col-md-6 side-image">

                <!-------------      image     ------------->

            </div>

            <div class="col-md-6 right">

                <div class="input-box">
                    <form action="${path}" method="post">
                        <header>Войти в аккаунт</header>
                        <div class="input-field">
                            <input type="text" name="username" class="input" id="username" required="" autocomplete="off">
                            <label for="email">Имя</label>
                        </div>
                        <div class="input-field">
                            <input type="password" name="password" class="input" id="password" required="">
                            <label for="pass">Пароль</label>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}" />

                        <div class="input-field">
                            <input type="submit" class="submit" value="Войти">
                        </div>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

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

<!--
< #macro login path>
    <form action="$ {path}" method="post">
        <div><label> Имя пользователя: <input type="text" name="username"/> </label></div>
        <div><label> Пароль: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="$ {_csrf.token}">
        <div><input type="submit" value="Принять"/></div>
    </form>
< /#macro>

-->


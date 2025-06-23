<#include "security.ftlh">
<#import "logreg.ftlh" as l>



<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-xxl">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="/">Стартовая </a>
        </li>
        <li class="nav-item active">
          <a class="nav-link" href="/login">Вход в систему </a>
        </li>
        <#if isAdmin >
          <li class="nav-item active">
            <a class="nav-link" href="/user">Список пользователей </a>
          </li>
            <li class="nav-item active">
                <a class="nav-link" href="/registration">Добавить пользователя</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/journal"> Журнал событий </a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/attendance"> Посещения </a>
            </li>

        </#if>
          <#if isUser>
              <li class="nav-item active">
                  <a class="nav-link" href="/attendance"> Посещения </a>
              </li>
          </#if>

          <#if isTeacher>
              <li class="nav-item active">
                  <a class="nav-link" href="/user">Список пользователей </a>
              </li>
              <li class="nav-item active">
                  <a class="nav-link" href="/attendance"> Посещения </a>
              </li>
          </#if>
      </ul>
      <form class="d-flex" role="search">
        <div class="navbar-text mr-3">${name}</div>
      </form>
      </button>
      <@l.logout/>
    </div>
  </div>
</nav>
  </div>
</nav>

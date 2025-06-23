package com.maria.web_access.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private Long user;  // Соответствует типу Usr.id (Long)
    private String role;

    // Обязательные конструкторы, геттеры, сеттеры, equals и hashCode
    public UserRoleId() {}

    public UserRoleId(Long user, String role) {
        this.user = user;
        this.role = role;
    }

    // Геттеры и сеттеры
    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return user.equals(that.user) && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
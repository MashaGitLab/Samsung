package com.maria.web_access.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class) // Для составного ключа
public class UserRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usr user;

    @Id
    @Column(name = "roles")
    private String role;

    // Геттеры и сеттеры
    public Usr getUser() {
        return user;
    }

    public void setUser(Usr user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
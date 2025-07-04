package com.maria.web_access.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, TEACHER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

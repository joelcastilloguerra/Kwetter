package com.thomasvz.kwetter.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_MODERATOR, ROLE_USER;

    public String getAuthority() {
        return name();
    }
}

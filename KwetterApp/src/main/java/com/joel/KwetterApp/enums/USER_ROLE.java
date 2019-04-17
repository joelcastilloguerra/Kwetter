package com.joel.KwetterApp.enums;

public enum USER_ROLE {
    NORMAL_USER,
    MODERATOR;

    public String getAuthority(){

        return name();

    }
}

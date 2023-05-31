package com.timesete.projeto5.model.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserAccessType {
    ADMIN("ROLE_ADMIN"),
    ANALYST("ROLE_ANALYST"),
    STOCK("ROLE_STOCK");

    private final String accessType;

    private UserAccessType(String accessType) {
       this.accessType = accessType;
    }

    public SimpleGrantedAuthority toSimpleGrantedAuthority() {
        return new SimpleGrantedAuthority(this.accessType);
    }
}

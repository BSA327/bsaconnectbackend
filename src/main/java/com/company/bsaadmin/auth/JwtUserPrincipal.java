package com.company.bsaadmin.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserPrincipal {

    private final Long userId;
    private final String username;
    private final String role;

    public JwtUserPrincipal(Long userId, String username,String role) {
        this.userId = userId;
        this.username = username;
        this.role=role;
    }

}

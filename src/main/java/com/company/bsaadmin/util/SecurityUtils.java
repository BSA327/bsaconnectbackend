package com.company.bsaadmin.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.company.bsaadmin.auth.JwtUserPrincipal;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static JwtUserPrincipal getPrincipal() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
            authentication.getPrincipal() == null) {
            return null;
        }

        return (JwtUserPrincipal) authentication.getPrincipal();
    }

    public static Long getUserId() {

        JwtUserPrincipal principal = getPrincipal();

        return principal != null
                ? principal.getUserId()
                : null;
    }

    public static String getUsername() {

        JwtUserPrincipal principal = getPrincipal();

        return principal != null
                ? principal.getUsername()
                : null;
    }
    
    public static String getRole() {

        JwtUserPrincipal principal = getPrincipal();

        return principal != null
                ? principal.getRole()
                : null;
    }
}

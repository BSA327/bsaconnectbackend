package com.company.bsaadmin.auth;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
            !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        
        JwtUserPrincipal principal =
                (JwtUserPrincipal) authentication.getPrincipal();

        return Optional.of(principal.getUserId());
    }
}
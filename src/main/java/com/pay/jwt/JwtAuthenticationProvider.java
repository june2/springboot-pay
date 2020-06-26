package com.pay.jwt;

import com.pay.api.user.model.User;
import com.pay.exception.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        User user = jwtService.verify((String) authentication.getCredentials());
        return Optional.ofNullable(user).map(res -> {
            return new JwtAuthenticatedProfile(res);
        }).orElseThrow(()-> new JwtAuthenticationException("Failed to verify token", null));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}

package com.blogspot.dinhtienthuan.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object details = authentication.getDetails();
        if (details instanceof PreWebAuthenticationDetails) {
            PreWebAuthenticationDetails authenticationDetails = (PreWebAuthenticationDetails) details;
            System.out.println(authenticationDetails.getExpiredTime());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}

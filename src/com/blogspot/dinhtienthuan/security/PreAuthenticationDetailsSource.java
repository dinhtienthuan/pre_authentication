package com.blogspot.dinhtienthuan.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class PreAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        System.out.println("In PreAuthenticationDetailsSource, buildDetails() method.");
        return new PreWebAuthenticationDetails(context);
    }

}

package com.blogspot.dinhtienthuan.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class FormLoginAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        System.out.println("In FormLoginAuthenticationDetailsSource, buildDetails() method.");
        return new FormLoginWebAuthenticationDetails(context);
    }

}

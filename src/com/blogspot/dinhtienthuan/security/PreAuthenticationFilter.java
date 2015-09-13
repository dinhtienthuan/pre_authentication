package com.blogspot.dinhtienthuan.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.blogspot.dinhtienthuan.cryptography.Cryptography;

public class PreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Autowired
    private Cryptography cryptography;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String username = "";
        String encryptedValue = request.getParameter("val");
        if (encryptedValue == null || encryptedValue.isEmpty()) {
            return username;
        }

        String primaryDecryptedValue = cryptography.decrypt(encryptedValue);
        String[] parts = primaryDecryptedValue.split(";");

        if (parts.length == 4) {
            username = cryptography.decrypt(parts[0]);
        }
        System.out
                .println("In UrlParametersAuthenticationFilter, getPreAuthenticatedPrincipal() method, the request method: "
                        + request.getMethod() + " has " + request.getParameterMap().size() + " parameter(s).");

        return username;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        String password = "";
        String encryptedValue = request.getParameter("val");
        if (encryptedValue == null || encryptedValue.isEmpty()) {
            return password;
        }

        String primaryDecryptedValue = cryptography.decrypt(encryptedValue);
        String[] parts = primaryDecryptedValue.split(";");

        if (parts.length == 4) {
            password = cryptography.decrypt(parts[1]);
        }
        System.out
                .println("In UrlParametersAuthenticationFilter, getPreAuthenticatedPrincipal() method, the request method: "
                        + request.getMethod() + " has " + request.getParameterMap().size() + " parameter(s).");

        return password;
    }

}

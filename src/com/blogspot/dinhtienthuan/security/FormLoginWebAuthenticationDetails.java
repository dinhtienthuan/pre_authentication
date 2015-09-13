package com.blogspot.dinhtienthuan.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Configurable(autowire = Autowire.BY_TYPE, preConstruction = true)
public class FormLoginWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 1882712251107122761L;

    private final String domain;

    public FormLoginWebAuthenticationDetails(HttpServletRequest request) {
        super(request);

        domain = request.getParameter("domain");
        System.out
                .println("In FormLoginWebAuthenticationDetails, getPreAuthenticatedPrincipal() method, the request method: "
                        + request.getMethod() + " has " + request.getParameterMap().size() + " parameter(s).");
    }

    public String getDomain() {
        return domain;
    }

}
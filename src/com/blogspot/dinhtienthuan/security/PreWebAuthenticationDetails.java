package com.blogspot.dinhtienthuan.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.blogspot.dinhtienthuan.cryptography.Cryptography;

@Configurable(autowire = Autowire.BY_TYPE, preConstruction = true)
public class PreWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 1882712251107122761L;

    @Autowired
    private Cryptography cryptography;

    private final String domain;
    private final String expiredTime;

    public PreWebAuthenticationDetails(HttpServletRequest request) {
        super(request);

        String tempDomain = "";
        String tempExpiredTime = "";
        String encryptedValue = request.getParameter("val");
        if (encryptedValue == null || encryptedValue.isEmpty()) {
            tempDomain = "";
            tempExpiredTime = "";
        } else {
            String primaryDecryptedValue = cryptography.decrypt(encryptedValue);
            String[] parts = primaryDecryptedValue.split(";");

            if (parts.length == 4) {
                tempDomain = cryptography.decrypt(parts[2]);
                tempExpiredTime = cryptography.decrypt(parts[3]);
            }
        }
        System.out
                .println("In PreWebAuthenticationDetails, getPreAuthenticatedPrincipal() method, the request method: "
                        + request.getMethod() + " has " + request.getParameterMap().size() + " parameter(s).");
        domain = tempDomain;
        expiredTime = tempExpiredTime;
    }

    public String getDomain() {
        return domain;
    }

    public String getExpiredTime() {
        return expiredTime;
    }
}

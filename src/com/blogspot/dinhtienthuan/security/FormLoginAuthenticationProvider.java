package com.blogspot.dinhtienthuan.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class FormLoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        FormLoginWebAuthenticationDetails details = (FormLoginWebAuthenticationDetails) authentication.getDetails();
        System.out.println("Domain: " + details.getDomain());
        if ("iprism".equals(username)) {
            System.out.println("User authenticated.");
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            return new User(authentication.getPrincipal().toString(), authentication.getCredentials().toString(), true,
                    true, true, true, grantedAuthorities);
        }
        System.out.println("Invalid.");
        throw new UsernameNotFoundException("The username/password is invalid.");
    }

}

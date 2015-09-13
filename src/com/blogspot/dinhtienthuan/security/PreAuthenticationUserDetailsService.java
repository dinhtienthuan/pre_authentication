package com.blogspot.dinhtienthuan.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PreAuthenticationUserDetailsService implements AuthenticationUserDetailsService<Authentication> {

    @Override
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
        System.out.println("In PreAuthenticationUserDetailsService.");
        System.out.println("Details is null: " + (authentication.getDetails() == null));
        System.out.println("Principals: " + authentication.getPrincipal().toString());
        System.out.println("Credentials: " + authentication.getCredentials());
        if ("iprism".equals(authentication.getPrincipal().toString())) {
            System.out.println("User authenticated.");
            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
            return new User(authentication.getPrincipal().toString(), authentication.getCredentials().toString(), true,
                    true, true, true, grantedAuthorities);
        }
        throw new UsernameNotFoundException("The username/password is invalid or login time is expired");
    }

}

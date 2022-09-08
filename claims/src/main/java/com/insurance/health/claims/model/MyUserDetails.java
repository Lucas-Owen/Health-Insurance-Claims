package com.insurance.health.claims.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialNonExpired;
    private boolean isEnabled;

    public MyUserDetails(String username,
                         String password,
                         Collection<? extends GrantedAuthority> grantedAuthorities,
                         boolean isAccountNonExpired,
                         boolean isAccountNonLocked,
                         boolean isCredentialNonExpired,
                         boolean isEnabled) {
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialNonExpired = isCredentialNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

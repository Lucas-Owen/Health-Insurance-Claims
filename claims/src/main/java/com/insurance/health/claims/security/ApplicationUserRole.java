package com.insurance.health.claims.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.insurance.health.claims.security.ApplicationUserPermission.CLAIMS_READ;
import static com.insurance.health.claims.security.ApplicationUserPermission.CLAIMS_WRITE;

public enum ApplicationUserRole {
    ADMIN(new HashSet<ApplicationUserPermission>(Set.of(CLAIMS_READ, CLAIMS_WRITE))),
    USER(new HashSet<ApplicationUserPermission>(Set.of(CLAIMS_READ))),
    PROVIDER(new HashSet<ApplicationUserPermission>(Set.of(CLAIMS_WRITE)));

    private final Set<ApplicationUserPermission> permissions;
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authorities = permissions
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

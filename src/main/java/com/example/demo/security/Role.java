package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum Role {
    ;

    static final String OPERATIONAL_MANAGER = "OPERATIONAL_MANAGER";
    static final String REGULAR_EMPLOYEE = "REGULAR_EMPLOYEE";

    private static final Set<String> ALL = Set.of(
            OPERATIONAL_MANAGER,
            REGULAR_EMPLOYEE
    );

    static GrantedAuthority grantedAuthority(String role) {
        if (!ALL.contains(role)) {
            throw new IllegalArgumentException("Unknown role: " + role);
        }
        return new SimpleGrantedAuthority("ROLE_" + role);
    }



}

package com.finalproject.easyjob.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * An enumeration depicting all the different roles of the UserDetails
 */
public enum Role implements GrantedAuthority {
    CANDIDATE,RECRUITER,ADMIN;

    public String getRole() {
        return name();
    }

    /**
     * IMPORTANT : this is to define that this is a role, not just some plain authority.
     * Roles are prifixed with the "ROLE_" syntax in Spring Security.
     * @return
     */
    @Override
    public String getAuthority() {
        return "ROLE_" + getRole();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
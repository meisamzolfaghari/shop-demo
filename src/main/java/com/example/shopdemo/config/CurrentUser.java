package com.example.shopdemo.config;

import com.example.shopdemo.entity.Role;
import com.example.shopdemo.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Set;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRolesAsStringArray()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Set<Role> getRoles() {
        return user.getRoles();
    }

    @Override
    public boolean isEnabled() {
        return !user.isBlocked();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}

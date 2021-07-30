package com.example.shopdemo.entity;

public enum Role {

    USER, ADMIN;

    public String getRoleString() {
        return "ROLE_" + this.name();
    }
}

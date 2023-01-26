package com.test.e.commerce.e.commerce.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class UserFound extends User {

    private final String token;

    public UserFound(String token) {
        super(token,"",new ArrayList<GrantedAuthority>());
        this.token = token;
    }

    public String getToken(){return token; }
}
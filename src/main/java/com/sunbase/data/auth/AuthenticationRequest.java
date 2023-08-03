package com.sunbase.data.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String login_id;
    private String password;

    // Getters and setters
}


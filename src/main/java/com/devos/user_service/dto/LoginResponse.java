package com.devos.user_service.dto;

import java.util.List;

public class LoginResponse {
    private String jwtToken;
    private String username;
    private List<String> permissions;

    public LoginResponse(String jwtToken, String username, List<String> permissions) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.permissions = permissions;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}

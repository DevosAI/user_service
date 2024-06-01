package com.devos.user_service.dto;

import com.devos.user_service.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String pwd;
    private String username;
    private List<Role> roles;

}

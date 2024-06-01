package com.devos.user_service.controller;

import com.devos.user_service.dto.UserRequest;
import com.devos.user_service.model.User;
import com.devos.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("detail/{username}")
    public Optional<User> userDetail(@PathVariable("username") String username) {
        return userService.getDetail(username);
    }


    @PostMapping("/add")
    public UserRequest addUser(@RequestBody UserRequest user) {
        return user;
    }

}

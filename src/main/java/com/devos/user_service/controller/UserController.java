package com.devos.user_service.controller;

import com.devos.user_service.model.User;
import com.devos.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("detail/{username}")
    public Optional<User> userDetail(@PathVariable("username") String username) {
        return userService.getDetail(username);
    }


}

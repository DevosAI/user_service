package com.devos.user_service.service;

import com.devos.user_service.dao.UserDao;
import com.devos.user_service.dto.UserRequest;
import com.devos.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Optional<User> getDetail(String username){
        return userDao.findByUsername(username);
    }

    public User addUser(UserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPwd(passwordEncoder.encode(userRequest.getPwd()));
        user.setCreatedBy("1");
        user.setUpdatedBy("1");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setRoles(userRequest.getRoles());
        return userDao.save(user);
    }
}

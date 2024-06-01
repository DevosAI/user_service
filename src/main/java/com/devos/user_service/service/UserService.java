package com.devos.user_service.service;

import com.devos.user_service.dao.UserDao;
import com.devos.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public Optional<User> getDetail(String username){
        return userDao.findByUsername(username);
    }
}

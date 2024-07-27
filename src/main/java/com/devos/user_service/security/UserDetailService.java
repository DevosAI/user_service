package com.devos.user_service.security;


import com.devos.user_service.dao.UserDao;
import com.devos.user_service.model.Permission;
import com.devos.user_service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailService  implements UserDetailsService {


    private  static  final Logger logger= LoggerFactory.getLogger(AuthEntryPointJwt.class);

    private UserDao userDao;

    @Autowired
    public void UserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername");
        User user = userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not Found"));
        Collection<Permission> authorities = (Collection<Permission>) user.getAuthorities();
        System.out.println(authorities);
        logger.info("loadUserByUsername {}",user);
        return user;
    }

//    private Collection<GrantedAuthority> mapRoles(List<Role> roles) {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
}

package com.devos.user_service.controller;

import com.devos.user_service.dao.UserDao;
import com.devos.user_service.dto.LoginRequest;
import com.devos.user_service.dto.LoginResponse;
import com.devos.user_service.model.User;
import com.devos.user_service.security.AuthTokenFilter;
import com.devos.user_service.security.JWTUtils;
import com.devos.user_service.security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private JWTUtils jwtUtils;

    private  static  final Logger logger= LoggerFactory.getLogger(AuthTokenFilter.class);


    @Autowired
    private AuthenticationManager authenticationManger;
    @Autowired
    private UserDao userDao;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try{
            authentication=authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        }catch (AuthenticationException exception){
            logger.error(exception.toString());
            Map<String,Object> map=new HashMap<>();
            map.put("message","Bad credentials");
            map.put("status",false);
            return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwtToken= jwtUtils.generateTokenFromUsername(userDetails);
        List <String> roles=userDetails.getAuthorities().stream()
                            .map(item->item.getAuthority())
                            .toList();

        LoginResponse response=new LoginResponse(jwtToken,userDetails.getUsername(),roles);

        return ResponseEntity.ok(response);


    }


}

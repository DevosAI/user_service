package com.devos.user_service.controller;

import com.devos.user_service.dao.PermissionDao;
import com.devos.user_service.dao.RoleDao;
import com.devos.user_service.dto.PermissionRequest;
import com.devos.user_service.dto.RoleRequest;
import com.devos.user_service.dto.ServiceRequest;
import com.devos.user_service.dto.UserRequest;
import com.devos.user_service.model.Permission;
import com.devos.user_service.model.Role;
import com.devos.user_service.model.Service;
import com.devos.user_service.model.User;
import com.devos.user_service.service.PermissionService;
import com.devos.user_service.service.RoleService;
import com.devos.user_service.service.ServiceService;
import com.devos.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    //@PreAuthorize("hasRole('Admin')")
    @GetMapping("/detail/{username}")
    public Optional<User> userDetail(@PathVariable("username") String username) {
        return userService.getDetail(username);
    }


    @PostMapping("/user/add")
    public User addUser(@RequestBody UserRequest userRequest) {
        User user= userService.addUser(userRequest);
        return user;
    }

    @PostMapping("/role/add")
    public Role addRole(@RequestBody RoleRequest roleRequest) {
        Role role= roleService.addRole(roleRequest);
        return role;
    }

    @GetMapping("/role/{id}")
    public Optional<Role> getRole(@PathVariable("id") int id) {
        return roleDao.findById(id);
    }

    @PutMapping("/role/update/{roleId}")
    public Role updateRole(@PathVariable("roleId") int roleId, @RequestBody RoleRequest roleRequest) {
        Role role=roleService.updateRole(roleId,roleRequest);
        return role;
    }

    @PostMapping("/service/add")
    public Service addService(@RequestBody ServiceRequest serviceRequest) {
        Service service = serviceService.addService(serviceRequest);
        return service;
    }

    @GetMapping("/permission/{id}")
    public Optional<Permission> getPermission(@PathVariable("id") long id) {
        return permissionDao.findById(id);
    }

    @PostMapping("/permission/add")
    public Permission addPermission(@RequestBody PermissionRequest permissionRequest) {
        Permission permission=permissionService.addPermission(permissionRequest);
        return permission;
    }

    @PreAuthorize("hasAuthority('create_user')")
    @GetMapping("/service/test")
    public String Test(){
        return "test";
    }
}

package com.devos.user_service.service;

import com.devos.user_service.dao.RoleDao;
import com.devos.user_service.dto.RoleRequest;
import com.devos.user_service.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    public Role addRole(RoleRequest roleRequest) {
            Role role = new Role();
            role.setRoleName(roleRequest.getRoleName());
            role.setDescription(roleRequest.getDescription());
            return roleDao.save(role);
    }

    public  Role updateRole(int roleId, RoleRequest roleRequest) {
            Optional<Role> role=roleDao.findById(roleId);
            if(role.isPresent()) {
                role.get().setRoleName(roleRequest.getRoleName());
                role.get().setDescription(roleRequest.getDescription());
                return roleDao.save(role.get());
            }else{
                throw new RuntimeException("Role not found");
            }

    }
}

package com.devos.user_service.service;

import com.devos.user_service.dao.PermissionDao;
import com.devos.user_service.dto.PermissionRequest;
import com.devos.user_service.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;

    public Permission addPermission(PermissionRequest permissionRequest) {
        Permission permission = new Permission();
        permission.setName(permissionRequest.getName());
        permission.setDescription(permissionRequest.getDescription());
        permission.setSlug(permissionRequest.getSlug());
        return permissionDao.save(permission);
    }
}

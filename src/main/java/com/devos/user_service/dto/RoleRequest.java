package com.devos.user_service.dto;
import com.devos.user_service.model.Permission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RoleRequest {

    private String roleName;

    private String description;

    private HashSet<Permission> permission;

    public HashSet<Permission> getPermission() {
        return permission;
    }

    public void setPermission(HashSet<Permission> permission) {
        this.permission = permission;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.devos.user_service.dao;

import com.devos.user_service.model.Permission;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends CrudRepository<Permission, Long> {

}

package com.devos.user_service.dao;

import com.devos.user_service.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao  extends CrudRepository   <Role,Integer>{

}

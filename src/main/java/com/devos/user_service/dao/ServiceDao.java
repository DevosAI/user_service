package com.devos.user_service.dao;

import com.devos.user_service.model.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao  extends CrudRepository<Service,Integer> {

}

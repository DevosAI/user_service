package com.devos.user_service.service;

import com.devos.user_service.dao.ServiceDao;
import com.devos.user_service.dto.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    ServiceDao serviceDao;

    public com.devos.user_service.model.Service addService(ServiceRequest serviceRequest){
        com.devos.user_service.model.Service service = new com.devos.user_service.model.Service();
        service.setName(serviceRequest.getName());
        service.setDescription(serviceRequest.getDescription());
        service.setObject(serviceRequest.getObject());
        return serviceDao.save(service);
    }
}

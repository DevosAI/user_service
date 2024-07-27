package com.devos.user_service.dao;

import com.devos.user_service.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao  extends CrudRepository<User,Integer> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}

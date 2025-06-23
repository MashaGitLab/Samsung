package com.maria.web_access.domain.repo;

import com.maria.web_access.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long>{
    User findByUsername(String username);

    @Override
    List<User> findAll();


}

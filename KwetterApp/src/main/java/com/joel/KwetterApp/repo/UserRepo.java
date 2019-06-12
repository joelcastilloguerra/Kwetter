package com.joel.KwetterApp.repo;

import com.joel.KwetterApp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {

    User getById(Integer id);

    User findByUsername(String username);

    List<User> findByFirstnameContaining(String search);

    Boolean existsByUsername(String username);

    User findByTokenToken(String token);

}

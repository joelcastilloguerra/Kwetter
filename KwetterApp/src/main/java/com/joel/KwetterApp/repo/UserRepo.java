package com.joel.KwetterApp.repo;

import com.joel.KwetterApp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

    User getById(Integer id);

}

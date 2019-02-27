package com.joel.KwetterApp.service;

import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public String getUser() {

        User user = new User("joel","123");
        user.addToFollowers(new User("follower", "password"));
        userRepo.save(user);

        return "USER USER USER";

    }
}

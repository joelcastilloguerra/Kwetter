package com.joel.KwetterApp.service;

import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void add(User user) {

        //Save the user in the db
        userRepo.save(user);

    }

    public void changeUserInfo(User user) {

        //Change the user info in the db
        userRepo.save(user);

    }

    public void addFollower(int idIsBeingFollowed, int idIsFollowing){

        //Get the entire object from the ids
        User isBeingFollowed = userRepo.getById(idIsBeingFollowed);
        User isFollowing = userRepo.getById(idIsFollowing);

        //Add the follower and following to the objects
        isBeingFollowed.addToFollower(isFollowing);
        isFollowing.addToFollowing(isBeingFollowed);

        //Save the objects in the db
        userRepo.save(isBeingFollowed);
        userRepo.save(isFollowing);

    }

    public void removeFollower(int idIsBeingUnFollowed, int idIsUnFollowing) {

        //Get the entire object from the ids
        User IsBeingUnFollowed = userRepo.getById(idIsBeingUnFollowed);
        User IsUnFollowing = userRepo.getById(idIsUnFollowing);

        //Add the follower and following to the objects
        IsBeingUnFollowed.removeFollower(IsUnFollowing);
        IsUnFollowing.removeFollowing(IsBeingUnFollowed);

        //Save the objects in the db
        userRepo.save(IsBeingUnFollowed);
        userRepo.save(IsUnFollowing);

    }

    public List<User> getAll() {

        List<User> allUsers = new ArrayList<>();

        userRepo.findAll().forEach(allUsers::add);

        return allUsers;

    }

    public User get(int id) {

        return userRepo.getById(id);

    }
}

package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, value = "/user")
    public String getUser(){

        return userService.getUser();

    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST, consumes="application/json")
    public void changeUserInfo(@RequestBody User user){

        userService.changeUserInfo(user);

    }

    @RequestMapping(value = "/addFollower", method = RequestMethod.POST, consumes="application/json")
    public void addFollower(@RequestBody User followedUser, @RequestBody User followingUser){

        //the followingUser is following the followedUser

    }

}

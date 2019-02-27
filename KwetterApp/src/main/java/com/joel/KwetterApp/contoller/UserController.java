package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, value = "/user")
    public String getUser(){

        return userService.getUser();

    }

}

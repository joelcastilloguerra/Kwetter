package com.joel.KwetterApp.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(method= RequestMethod.POST, value = "/user")
    public String getUser(){

        return "USER USER USER";

    }

}

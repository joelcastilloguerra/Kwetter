package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, value = "/add")
    public void add(@RequestBody User user){

        userService.add(user);

    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST, consumes="application/json")
    public void changeUserInfo(@RequestBody User user){

        userService.changeUserInfo(user);

    }

    @RequestMapping(value = "/addFollower/{idIsBeingFollowed}/{idIsFollowing}", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void addFollower(@PathVariable(value = "idIsBeingFollowed") int idIsBeingFollowed, @PathVariable(value = "idIsFollowing") int idIsFollowing){

        //the isFollowing is following isBeingFollowed
        userService.addFollower(idIsBeingFollowed, idIsFollowing);

    }

}

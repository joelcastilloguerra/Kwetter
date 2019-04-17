package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.exception.CustomException;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();


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

    @RequestMapping(value = "/removeFollower/{idIsBeingUnFollowed}/{idIsUnFollowing}", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void removeFollower(@PathVariable(value = "idIsBeingUnFollowed") int idIsBeingUnFollowed, @PathVariable(value = "idIsUnFollowing") int idIsUnFollowing){

        //the IsUnFollowing is unfollowing isBeingUnFollowed
        userService.removeFollower(idIsBeingUnFollowed, idIsUnFollowing);

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public User getAllUsers(){

        return userService.getAll().get(1);

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable(value = "id") int id){

        return userService.get(id);

    }

    @PostMapping("/signin")
    public Map<String, String> login(@RequestBody User user) {

        if (user.getUsername() != null && user.getPassword() != null) {

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("token", userService.signin(user.getUsername(), user.getPassword()));

            return hashMap;

        } else {

            throw new CustomException("Username or password not supplied", HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }

    @PostMapping("/signup")
    public Map<String, String> signup(@RequestBody User user) {

        System.out.println(user.toString());

        if (user.getUsername() != null && user.getPassword() != null && user.getFirstname() != null && user.getLastname() != null && user.getEmail() != null) {

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("token", userService.signup(modelMapper.map(user, User.class)));

            return hashMap;

        } else {

            throw new CustomException("Username, Password, Firstname, Lastname or Email not supplied", HttpStatus.UNPROCESSABLE_ENTITY);

        }
    }

}

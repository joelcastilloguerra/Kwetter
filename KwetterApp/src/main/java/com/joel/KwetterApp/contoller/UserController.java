package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.exception.CustomException;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    private ModelMapper modelMapper = new ModelMapper();


    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST, consumes="application/json")
    public void changeUserInfo(HttpServletRequest req, @RequestBody User user){

        userService.changeUserInfo(user);

    }

    @RequestMapping(value = "/changeRole/{userId}", method = RequestMethod.POST)
    public void changeUserRole(HttpServletRequest req, @PathVariable(value = "userId") int userId) {

        userService.changeUserRole(userId, userService.whoami(req));

    }

    @RequestMapping(value = "/addFollower/{idIsBeingFollowed}", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void addFollower(HttpServletRequest req, @PathVariable(value = "idIsBeingFollowed") int idIsBeingFollowed){

        User user = userService.whoami(req);
        //the isFollowing is following isBeingFollowed
        userService.addFollower(idIsBeingFollowed, user.getId());

    }

    @RequestMapping(value = "/removeFollower/{idIsBeingUnFollowed}/{idIsUnFollowing}", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void removeFollower(@PathVariable(value = "idIsBeingUnFollowed") int idIsBeingUnFollowed, @PathVariable(value = "idIsUnFollowing") int idIsUnFollowing){

        //the IsUnFollowing is unfollowing isBeingUnFollowed
        userService.removeFollower(idIsBeingUnFollowed, idIsUnFollowing);

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(HttpServletRequest req){

        User user = userService.whoami(req);

        return user;

    }

    @RequestMapping(value = "/search/{searchString}", method = RequestMethod.GET)
    @ResponseBody
    public List<User> search(@PathVariable(value = "searchString") String searchString){

        return userService.search(searchString);

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

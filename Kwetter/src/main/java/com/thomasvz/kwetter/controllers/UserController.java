package com.thomasvz.kwetter.controllers;

import com.thomasvz.kwetter.dto.UserDataDTO;
import com.thomasvz.kwetter.dto.UserResponseDTO;
import com.thomasvz.kwetter.exception.CustomException;
import com.thomasvz.kwetter.model.Status;
import com.thomasvz.kwetter.model.User;
import com.thomasvz.kwetter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

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
    public Map<String, String> signup(@RequestBody UserDataDTO user) {
        if (user.getUsername() != null && user.getPassword() != null && user.getFirstname() != null && user.getLastname() != null && user.getEmail() != null) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("token", userService.signup(modelMapper.map(user, User.class)));
            return hashMap;
        } else {
            throw new CustomException("Username, Password, Firstname, Lastname or Email not supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/follow")
    public ResponseEntity<Status> followUser(HttpServletRequest req, @RequestBody UserDataDTO userToFollow) {
        if (userToFollow.getUsername() != null) {
            if (userService.followUser(userService.whoami(req), userService.search(userToFollow.getUsername()))) {
                return new ResponseEntity<>(new Status(true), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Status(false), HttpStatus.BAD_REQUEST);

        } else {
            throw new CustomException("Username of user to follow not supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Status> unfollowUser(HttpServletRequest req, @RequestBody UserDataDTO userToUnfollow) {
        if (userToUnfollow.getUsername() != null) {
            if (userService.unfollowUser(userService.whoami(req), userService.search(userToUnfollow.getUsername()))) {
                return new ResponseEntity<>(new Status(true), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Status(false), HttpStatus.BAD_REQUEST);

        } else {
            throw new CustomException("Username of user to unfollow not supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }
}

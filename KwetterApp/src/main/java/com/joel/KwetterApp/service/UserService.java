package com.joel.KwetterApp.service;

import com.joel.KwetterApp.config.JwtTokenProvider;
import com.joel.KwetterApp.enums.USER_ROLE;
import com.joel.KwetterApp.exception.CustomException;
import com.joel.KwetterApp.mail.MailController;
import com.joel.KwetterApp.mail.VerificationToken;
import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    MailController mailController = new MailController();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, new ArrayList<USER_ROLE>(){{ add(userRepo.findByUsername(username).getRole());}});
        } catch (AuthenticationException e) {
            throw new CustomException("Username or password invalid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User whoami(HttpServletRequest req) {

        return userRepo.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));

    }

    public String signup(User user) throws Exception {
        if (!userRepo.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<USER_ROLE> userRoles = new ArrayList<>();
            userRoles.add(USER_ROLE.NORMAL_USER);
            user.setUserRole(userRoles.get(0));
            user.setToken(new VerificationToken(false, UUID.randomUUID().toString()));
            userRepo.save(user);
            mailController.sendUserEmail(user);
            return jwtTokenProvider.createToken(user.getUsername(), new ArrayList<USER_ROLE>(){{ add(userRepo.findByUsername(user.getUsername()).getRole());}});
        } else {
            throw new CustomException("Username is already taken!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void add(User user) {

        //Save the user in the db
        userRepo.save(user);

    }

    public void changeUserInfo(User updatedProfile){

        //Change the user info in the db
        userRepo.save(updatedProfile);

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

    public List<User> search(String searchString) {

        return userRepo.findByFirstnameContaining(searchString);

    }

    public void changeUserRole(int userId, User user) {

        User changedUser = this.get(userId);

        if(user.getRole().equals(USER_ROLE.MODERATOR)){

            switch (changedUser.getRole()){

                case NORMAL_USER:
                    changedUser.setUserRole(USER_ROLE.MODERATOR);
                    break;
                case MODERATOR:
                    changedUser.setUserRole(USER_ROLE.NORMAL_USER);

            }

            userRepo.save(changedUser);

        }

    }
}

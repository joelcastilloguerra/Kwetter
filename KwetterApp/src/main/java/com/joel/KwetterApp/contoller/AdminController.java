package com.joel.KwetterApp.contoller;

import com.joel.KwetterApp.model.User;
import com.joel.KwetterApp.repo.UserRepo;
import com.joel.KwetterApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Scope("view")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepository;

    private DataModel<User> userModel;

    @PostConstruct
    public void init() {

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        userModel = new ListDataModel<>(users);
    }

    public void delete() {
        User user = userModel.getRowData();
        userService.delete(user.getId());

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        userModel = new ListDataModel<>(users);
    }

    public DataModel<User> getUserModel() {
        return userModel;
    }
}

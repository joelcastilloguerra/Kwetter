package com.thomasvz.kwetter.dto;

import com.thomasvz.kwetter.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDataDTO {

    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private String email;

    List<Role> roles;

}

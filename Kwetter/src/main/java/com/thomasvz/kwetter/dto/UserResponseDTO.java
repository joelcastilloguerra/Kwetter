package com.thomasvz.kwetter.dto;

import com.thomasvz.kwetter.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;

    private String username;

    private String firstname;
    private String lastname;

    private String email;

    private String bio;
    private String location;
    private String websiteUrl;
    private String profilePicturePath;

    List<Role> roles;
}

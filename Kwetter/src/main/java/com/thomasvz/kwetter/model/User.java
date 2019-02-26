package com.thomasvz.kwetter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    private String firstname;
    private String lastname;

    private String email;

    private String bio;
    private String location;
    private String websiteUrl;

    private String profilePicturePath;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="User_Followers")
    List<User> followers;

    public void follow(User user) {
        followers.add(user);
    }

    public void unfollow(User user) {
        int id = user.getId();
        followers.removeIf(follower -> follower.getId() == id);
    }

}

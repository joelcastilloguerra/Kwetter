package com.joel.KwetterApp.model;

import com.joel.KwetterApp.enums.USER_ROLE;
import com.joel.KwetterApp.mail.VerificationToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String bio;
    private String location;
    private String websiteUrl;
    private USER_ROLE userRole;

    @OneToOne(targetEntity = VerificationToken.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private VerificationToken token;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable(name="User_Followers")
    List<User> followers;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinTable(name="User_Following")
    List<User> following;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        followers = new ArrayList<>();
        following = new ArrayList<>();
    }

    public User(int id, String username, String password, String firstname, String lastname, String email, String bio, String location, String websiteUrl) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.location = location;
        this.websiteUrl = websiteUrl;

    }

    public User(int id, String username, String password, String firstname, String lastname, String email, String bio, String location, String websiteUrl, USER_ROLE userRole) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.location = location;
        this.websiteUrl = websiteUrl;
        this.userRole = userRole;
    }

    public User(int id, String username, String firstname, String lastname, String email, String bio, String location, String websiteUrl, USER_ROLE userRole) {

        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bio = bio;
        this.location = location;
        this.websiteUrl = websiteUrl;
        this.userRole = userRole;
    }

    public User(int id){

        this.id = id;

    }

    public User() {
    }

    public USER_ROLE getUserRole() {
        return userRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public ArrayList<Integer> getFollowers(){

        ArrayList<Integer> returnList = new ArrayList<>();

        for(User user : this.followers){

            returnList.add(user.getId());

        }

        return returnList;

    }

    public List<Integer> getFollowing(){

        ArrayList<Integer> returnList = new ArrayList<>();

        for(User user : this.following){

            returnList.add(user.getId());

        }

        return returnList;

    }

    public void setUserRole(USER_ROLE userRole) {
        this.userRole = userRole;
    }

    public void addToFollower(User user){

        this.followers.add(user);

    }

    public void removeFollower(User user){

        this.followers.remove(user);

    }

    public void addToFollowing(User user){

        this.following.add(user);

    }

    public void removeFollowing(User user){

        this.following.remove(user);

    }

    public USER_ROLE getRole() {

        return this.userRole;

    }

    public VerificationToken getToken() {
        return token;
    }

    public void setToken(VerificationToken token) {
        this.token = token;
    }
}

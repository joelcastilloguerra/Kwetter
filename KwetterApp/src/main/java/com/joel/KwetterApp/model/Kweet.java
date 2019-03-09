package com.joel.KwetterApp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet {

    @Id
    @GeneratedValue
    private int id;

    private String content;
    private Date dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User poster;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="KweetLikedBy")
    private List<User> likedBy;

    public Kweet() {
    }

    public Kweet(String content, Date dateTime, User poster, List<User> likedBy) {
        this.content = content;
        this.dateTime = dateTime;
        this.poster = poster;
        this.likedBy = likedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void addLikedBy(User user) {
        this.likedBy.add(user);
    }

    public void removeLikedBy(User user) {
        this.likedBy.remove(user);
    }
}

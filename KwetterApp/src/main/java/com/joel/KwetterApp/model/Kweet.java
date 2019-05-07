package com.joel.KwetterApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Kweet implements Comparable<Kweet>{

    @Id
    @GeneratedValue
    private int id;

    @Column(name="content")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private User poster;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="KweetLikedBy")
    private List<User> likedBy;

    public Kweet() {
    }

    public Kweet(int id, String content, Date dateTime, User poster, List<User> likedBy) {
        this.id = id;
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

    public int getPoster() {
        return poster.getId();
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

    @Override
    public int compareTo(Kweet o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}

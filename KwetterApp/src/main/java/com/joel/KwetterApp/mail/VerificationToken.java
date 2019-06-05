package com.joel.KwetterApp.mail;

import com.joel.KwetterApp.model.User;

import javax.persistence.*;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue
    private int id;
    private Boolean verified;
    private String token;



    public VerificationToken(Boolean verified, String token) {
        this.verified = verified;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

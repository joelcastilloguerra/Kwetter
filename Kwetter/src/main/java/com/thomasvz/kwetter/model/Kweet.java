package com.thomasvz.kwetter.model;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class Kweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private DateTime createdAt;
}

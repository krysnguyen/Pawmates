package com.dogmates.dogmates.core.walk.domain;


import lombok.Data;

@Data
public class Walk {
    private String id;

    private String title;

    private long date;

    private long time;

    private int duration;

    private String location;

    private String description;

    private String userId;

    private String firstName;

    private String lastName;
}

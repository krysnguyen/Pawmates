package com.dogmates.dogmates.db.walk;

import lombok.Data;

import java.util.List;

@Data
public class WalkEntity {
    private String title;

    private long date;

    private long time;

    private int duration;

    private String location;

    private String description;

    private String userId;

    private String firstName;

    private String lastName;

    private long expiryTimeStamp;

    private List<String> joinedUsers;
}

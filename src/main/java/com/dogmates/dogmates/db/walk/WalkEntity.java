package com.dogmates.dogmates.db.walk;

import lombok.Data;

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
}

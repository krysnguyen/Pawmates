package com.dogmates.dogmates.core.user.domain;

import lombok.Data;

@Data
public class User {
    private String id;

    private String firstName;

    private String lastName;

    private String bio;

    private int numberOfDogs;

    private String dogTypes;

    private String walkTypes;
}

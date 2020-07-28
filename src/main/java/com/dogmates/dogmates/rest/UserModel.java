package com.dogmates.dogmates.rest;

import lombok.Data;

@Data
public class UserModel {
    private String id;

    private String firstName;

    private String lastName;

    private String bio;

    private int numberOfDogs;

    private String dogTypes;

    private String walkTypes;
}

package com.dogmates.dogmates.core.user.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private long birthday;

    private String bio;

    private int numberOfDogs;

    private List<String> dogTypes;

    private List<String> walkTypes;

    private List<String> liked = new ArrayList<>();

    private List<String> disliked = new ArrayList<>();

    private List<String> likedBy = new ArrayList<>();

    private List<String> matches = new ArrayList<>();
}

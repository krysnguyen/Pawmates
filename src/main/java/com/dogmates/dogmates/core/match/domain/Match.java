package com.dogmates.dogmates.core.match.domain;

import lombok.Data;

import java.util.List;

@Data
public class Match {
    private String id;

    private String firstName;

    private String lastName;

    private String bio;

    private List<String> images;
}

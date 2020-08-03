package com.dogmates.dogmates.rest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends RepresentationModel<UserModel> {
    private String userId;

    private String firstName;

    private String lastName;

    private String bio;

    private int numberOfDogs;

    private String dogTypes;

    private String walkTypes;
}

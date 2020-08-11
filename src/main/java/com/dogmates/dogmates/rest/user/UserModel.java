package com.dogmates.dogmates.rest.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends RepresentationModel<UserModel> {
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private LocalDate birthday;

    private String bio;

    @NotEmpty
    private List<String> dogTypes;

    private String dogName;

    private List<String> walkTypes;

    private List<String> images;

    private List<String> matches;
}
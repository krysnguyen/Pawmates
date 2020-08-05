package com.dogmates.dogmates.core.user.usecase.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserCmd {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String bio;

    private int numberOfDogs;

    private String dogTypes;

    private String walkTypes;

    @NotBlank
    private String userId;
}

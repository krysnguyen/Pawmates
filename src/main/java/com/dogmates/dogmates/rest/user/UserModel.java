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

    private LocalDate birthday;

    private String bio;

    @NotEmpty
    private List<String> dogTypes;

    private List<String> walkTypes;
}

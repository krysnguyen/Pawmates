package com.dogmates.dogmates.rest.match;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchModel extends RepresentationModel<MatchModel> {
    private String userId;

    private String firstName;

    private String lastName;

    private String bio;
}

package com.dogmates.dogmates.rest.walk;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class WalkModel extends RepresentationModel<WalkModel> {
    private String id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private String location;
    private String description;
    private UserModel user;

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class UserModel extends RepresentationModel<UserModel> {
        private String userId;
        private String firstName;
        private String lastName;
    }
}

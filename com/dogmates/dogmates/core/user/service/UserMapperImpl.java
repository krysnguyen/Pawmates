package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-27T10:47:34-0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (AdoptOpenJDK)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserCmd cmd) {
        if ( cmd == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( cmd.getFirstName() );
        user.setLastName( cmd.getLastName() );
        user.setBio( cmd.getBio() );
        user.setNumberOfDogs( cmd.getNumberOfDogs() );
        user.setDogTypes( cmd.getDogTypes() );
        user.setWalkTypes( cmd.getWalkTypes() );

        return user;
    }
}

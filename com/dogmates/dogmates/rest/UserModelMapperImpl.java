package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.domain.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-07-27T10:47:34-0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (AdoptOpenJDK)"
)
public class UserModelMapperImpl implements UserModelMapper {

    @Override
    public UserModel toModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setFirstName( user.getFirstName() );
        userModel.setLastName( user.getLastName() );
        userModel.setBio( user.getBio() );
        userModel.setNumberOfDogs( user.getNumberOfDogs() );
        userModel.setDogTypes( user.getDogTypes() );
        userModel.setWalkTypes( user.getWalkTypes() );

        return userModel;
    }
}

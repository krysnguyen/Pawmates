package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.domain.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserModelMapper {
    UserModel toModel(User user);
}

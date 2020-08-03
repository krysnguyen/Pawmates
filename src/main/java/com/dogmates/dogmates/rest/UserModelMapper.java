package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserModelMapper {
    @Mapping(source = "id", target = "userId")
    UserModel toModel(User user);
}

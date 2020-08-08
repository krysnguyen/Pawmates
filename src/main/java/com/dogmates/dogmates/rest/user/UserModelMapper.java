package com.dogmates.dogmates.rest.user;

import com.dogmates.dogmates.core.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserModelMapper {
    @Mapping(source = "id", target = "userId")
    @Mapping(source = "birthday", target = "birthday", ignore = true)
    UserModel toModel(User user);
}

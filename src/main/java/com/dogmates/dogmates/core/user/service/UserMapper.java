package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(source = "userId", target = "id")
    User toUser(CreateUserCmd cmd);
}

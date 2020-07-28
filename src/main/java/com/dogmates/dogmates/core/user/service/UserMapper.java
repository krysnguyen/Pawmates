package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(CreateUserCmd cmd);
}

package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import com.dogmates.dogmates.core.user.usecase.update.UpdateUserCmd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    @Mapping(source = "userId", target = "id")
    User toUser(CreateUserCmd cmd);

    void updateUser(UpdateUserCmd cmd, @MappingTarget User user);
}

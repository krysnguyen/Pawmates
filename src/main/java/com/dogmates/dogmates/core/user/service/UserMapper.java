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
    @Mapping(source = "birthday", target = "birthday", ignore = true)
    User toUser(CreateUserCmd cmd);

    @Mapping(source = "birthday", target = "birthday", ignore = true)
    void updateUser(UpdateUserCmd cmd, @MappingTarget User user);
}

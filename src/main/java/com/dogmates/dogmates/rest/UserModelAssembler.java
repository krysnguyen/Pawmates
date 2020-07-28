package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.domain.User;
import org.springframework.stereotype.Component;

import static org.mapstruct.factory.Mappers.getMapper;

@Component
public class UserModelAssembler {
    private final UserModelMapper mapper = getMapper(UserModelMapper.class);

    public UserModel toModel(User user) {
        return mapper.toModel(user);
    }
}

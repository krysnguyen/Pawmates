package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.CreateUserPort;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.mapstruct.factory.Mappers.getMapper;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;

    private UserMapper mapper = getMapper(UserMapper.class);

    @Override
    public User create(CreateUserCmd cmd) throws ExecutionException, InterruptedException {
        val user = mapper.toUser(cmd);
        val birthday = cmd.getBirthday().toEpochDay();
        user.setBirthday(birthday);
        return createUserPort.create(user);
    }
}

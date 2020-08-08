package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.core.user.port.UpdateUserPort;
import com.dogmates.dogmates.core.user.usecase.update.UpdateUserCmd;
import com.dogmates.dogmates.core.user.usecase.update.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static org.mapstruct.factory.Mappers.getMapper;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
    private final UpdateUserPort updateUserPort;
    private final GetUserPort getUserPort;

    private final UserMapper mapper = getMapper(UserMapper.class);

    @Override
    public User update(UpdateUserCmd cmd, String userId) throws ExecutionException, InterruptedException {
        val user = getUserPort.getUser(userId);
        mapper.updateUser(cmd, user);
        return updateUserPort.update(user);
    }
}

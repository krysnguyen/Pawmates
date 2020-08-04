package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.DislikeUserPort;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.core.user.usecase.update.DislikeUserUseCase;
import com.dogmates.dogmates.core.user.usecase.update.IdCmd;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class DislikeUserService implements DislikeUserUseCase {
    private final DislikeUserPort dislikeUserPort;
    private final GetUserPort getUserPort;

    @Override
    public User dislike(String userId, IdCmd cmd) throws ExecutionException, InterruptedException {
        val user = getUserPort.getUser(userId);
        return dislikeUserPort.dislike(user, cmd.getId());
    }
}

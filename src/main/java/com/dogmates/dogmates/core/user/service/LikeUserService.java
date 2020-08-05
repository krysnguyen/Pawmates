package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.core.user.port.LikeUserPort;
import com.dogmates.dogmates.core.user.usecase.update.IdCmd;
import com.dogmates.dogmates.core.user.usecase.update.LikeUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class LikeUserService implements LikeUserUseCase {
    private final LikeUserPort likeUserPort;
    private final GetUserPort getUserPort;

    @Override
    public User like(String userId, IdCmd cmd) throws ExecutionException, InterruptedException {
        val user = getUserPort.getUser(userId);
        val likedUser = getUserPort.getUser(cmd.getId());
        return likeUserPort.like(user, likedUser);
    }
}

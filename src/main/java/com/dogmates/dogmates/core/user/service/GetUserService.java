package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.core.user.usecase.read.GetUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {
    private final GetUserPort getUserPort;

    @Override
    public User getUser(String userId) throws ExecutionException, InterruptedException {
        return getUserPort.getUser(userId);
    }
}

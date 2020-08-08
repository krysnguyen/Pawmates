package com.dogmates.dogmates.core.walk.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.UpdateWalkPort;
import com.dogmates.dogmates.core.walk.usecase.update.JoinWalkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class JoinWalkService implements JoinWalkUseCase {
    private final UpdateWalkPort updateWalkPort;

    @Override
    public Walk join(String userId, String walkId, String joiningUserId) throws ExecutionException, InterruptedException {
        return updateWalkPort.join(userId, walkId, joiningUserId);
    }
}

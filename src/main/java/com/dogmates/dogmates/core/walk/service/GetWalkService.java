package com.dogmates.dogmates.core.walk.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.GetWalkPort;
import com.dogmates.dogmates.core.walk.usecase.read.GetWalkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetWalkService implements GetWalkUseCase {
    private final GetWalkPort getWalkPort;

    @Override
    public Walk get(String userId, String walkId) throws ExecutionException, InterruptedException {
        return getWalkPort.getWalk(userId, walkId);
    }
}

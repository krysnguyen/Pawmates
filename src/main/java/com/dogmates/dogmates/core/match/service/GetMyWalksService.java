package com.dogmates.dogmates.core.match.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.GetWalkPort;
import com.dogmates.dogmates.core.walk.usecase.read.GetMyWalksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetMyWalksService implements GetMyWalksUseCase {
    private final GetWalkPort getWalkPort;


    @Override
    public List<Walk> getMyWalks(String userId) throws ExecutionException, InterruptedException {
        return getWalkPort.getMyWalks(userId);
    }
}

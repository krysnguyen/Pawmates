package com.dogmates.dogmates.core.match.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.GetWalkPort;
import com.dogmates.dogmates.core.walk.usecase.read.GetMyRelatedWalks;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetMyRelatedWalksService implements GetMyRelatedWalks {
    private final GetWalkPort getWalkPort;


    @Override
    public List<Walk> getWalks(String userId) throws ExecutionException, InterruptedException {
        return getWalkPort.getMyRelatedWalks(userId);
    }
}

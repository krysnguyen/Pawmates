package com.dogmates.dogmates.core.walk.port;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetWalkPort {
    Walk getWalk(String userId, String walkId) throws ExecutionException, InterruptedException;

    List<Walk> getMyWalks(String userId) throws ExecutionException, InterruptedException;
}

package com.dogmates.dogmates.core.walk.port;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.concurrent.ExecutionException;

public interface UpdateWalkPort {
    Walk join(String userId, String walkId, String joiningUserId) throws ExecutionException, InterruptedException;
}

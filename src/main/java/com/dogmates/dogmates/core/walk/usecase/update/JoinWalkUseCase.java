package com.dogmates.dogmates.core.walk.usecase.update;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.concurrent.ExecutionException;

public interface JoinWalkUseCase {
    Walk join(String userId, String walkId, String joiningUserId) throws ExecutionException, InterruptedException;
}

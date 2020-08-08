package com.dogmates.dogmates.core.walk.usecase.read;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.concurrent.ExecutionException;

public interface GetWalkUseCase {
    Walk get(String userId, String walkId) throws ExecutionException, InterruptedException;
}

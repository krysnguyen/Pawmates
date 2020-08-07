package com.dogmates.dogmates.core.walk.usecase.create;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.concurrent.ExecutionException;

public interface CreateWalkUseCase {
    /**
     * Create a walk in the DB.
     *
     * @param cmd The request body of the walk to create.
     * @param userId The ID of the user creating the walk.
     * @return The created Walk.
     */
    Walk create(CreateWalkCmd cmd, String userId) throws ExecutionException, InterruptedException;
}

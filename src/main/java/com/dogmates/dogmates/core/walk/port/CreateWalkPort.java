package com.dogmates.dogmates.core.walk.port;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.concurrent.ExecutionException;

public interface CreateWalkPort {
    /**
     * Create a walk in the DB.
     *
     * @param walk   The walk to create.
     * @param userId The ID of the user creating the walk.
     * @return The created walk.
     */
    Walk create(Walk walk, String userId) throws ExecutionException, InterruptedException;
}

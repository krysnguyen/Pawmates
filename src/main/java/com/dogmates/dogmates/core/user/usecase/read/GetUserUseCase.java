package com.dogmates.dogmates.core.user.usecase.read;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface GetUserUseCase {
    /**
     * Get user from the DB
     *
     * @param userId The id of the user to get
     * @return The user from the DB
     */
    User getUser(String userId) throws ExecutionException, InterruptedException;
}

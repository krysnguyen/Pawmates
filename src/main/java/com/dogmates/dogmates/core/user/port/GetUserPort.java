package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetUserPort {
    /**
     * Get all the users from the DB
     *
     * @return A list of users
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<User> getAll() throws ExecutionException, InterruptedException;

    /**
     * Get a user from the DB
     *
     * @param id The id of the user to get
     * @return The user from the DB
     * @throws ExecutionException
     * @throws InterruptedException
     */
    User getUser(String id) throws ExecutionException, InterruptedException;

    /**
     * Get users to match with after filtering.
     *
     * @param userId The ID of the user to get potential matches for.
     * @return The list of potential users to match with.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<User> getPotentialMatches(String userId) throws ExecutionException, InterruptedException;

}

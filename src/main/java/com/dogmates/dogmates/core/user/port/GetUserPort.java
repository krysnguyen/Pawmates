package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetUserPort {
    /**
     * Get all the users from the DB
     *
     * @return A list of users
     */
    List<User> getAll() throws ExecutionException, InterruptedException;

    /**
     * Get a user from the DB
     *
     * @param id The id of the user to get
     * @return The user from the DB
     */
    User getUser(String id) throws ExecutionException, InterruptedException;
}

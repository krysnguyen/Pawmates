package com.dogmates.dogmates.core.user.usecase.read;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetUsersUseCase {
    /**
     * Get all users from the DB
     *
     * @return A list of users
     */
    List<User> getUsers() throws ExecutionException, InterruptedException;
}

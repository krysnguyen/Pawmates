package com.dogmates.dogmates.core.user.usecase.update;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface DislikeUserUseCase {
    /**
     * Dislike a user and add their ID to the disliked list in the DB.
     *
     * @param userId The ID of the user disliking.
     * @param cmd The ID object of the disliked user.
     * @return The User after disliking.
     */
    User dislike(String userId, IdCmd cmd) throws ExecutionException, InterruptedException;
}

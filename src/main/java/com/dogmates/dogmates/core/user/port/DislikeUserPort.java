package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface DislikeUserPort {
    /**
     * Dislike a user and add their ID to the disliked list in the DB.
     *
     * @param user           The user disliking.
     * @param dislikedUserId The ID of the disliked user.
     * @return The User after disliking.
     */
    User dislike(User user, String dislikedUserId) throws ExecutionException, InterruptedException;
}

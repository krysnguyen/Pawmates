package com.dogmates.dogmates.core.user.usecase.update;

import com.dogmates.dogmates.core.user.domain.User;

public interface LikeUserUseCase {
    /**
     * Add a user id to liked list of the user in the DB
     *
     * @param userId The user that is liking the other user
     * @param cmd    The user that is liked
     * @return The User after adding ID to the liked list
     */
    User like(String userId, IdCmd cmd);
}

package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

public interface LikeUserPort {
    /**
     * Add ID to liked list of the user in the DB
     *
     * @param userId      The user that like the other user
     * @param likedUserId The id of the user that is liked
     * @return The User after adding ID to the liked list
     */
    User like(String userId, String likedUserId);
}

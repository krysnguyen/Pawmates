package com.dogmates.dogmates.core.user.usecase.read;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetPotentialMatchesUseCase {
    /**
     * Get all users after filtering based on the user's liked, disliked and matches list from the DB.
     *
     * @param userId The ID of the user to get potential matches for.
     * @return The list of potential users to match with.
     */
    List<User> getPotentialMatches(String userId) throws ExecutionException, InterruptedException;
}

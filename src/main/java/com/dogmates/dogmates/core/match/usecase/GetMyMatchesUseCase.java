package com.dogmates.dogmates.core.match.usecase;

import com.dogmates.dogmates.core.match.domain.Match;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetMyMatchesUseCase {
    /**
     * Get user's matches list from the DB.
     *
     * @param userId The ID of the user to get matches for.
     * @return The list of user's matches.
     */
    List<Match> getMyMatches(String userId) throws ExecutionException, InterruptedException;
}

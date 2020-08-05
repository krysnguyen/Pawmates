package com.dogmates.dogmates.core.match.port;

import com.dogmates.dogmates.core.match.domain.Match;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetMatchPort {
    /**
     * Get the user's matches.
     *
     * @param userId The ID of the user to get the matches for
     * @return The list of matches for the user.
     */
    List<Match> getMyMatches(String userId) throws ExecutionException, InterruptedException;
}

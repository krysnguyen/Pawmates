package com.dogmates.dogmates.db.match;

import com.dogmates.dogmates.core.match.domain.Match;
import com.dogmates.dogmates.core.match.port.GetMatchPort;
import com.dogmates.dogmates.db.common.FirestoreQueryHelper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class GetMatchAdapter implements GetMatchPort {
    private final FirestoreQueryHelper firestoreQueryHelper;

    @Override
    public List<Match> getMyMatches(String userId) throws ExecutionException, InterruptedException {
        val matchedUserIds = firestoreQueryHelper.getUserMatchesList(userId);
        return firestoreQueryHelper.getMatches(matchedUserIds.getMatches());
    }
}

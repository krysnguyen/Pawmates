package com.dogmates.dogmates.db.common;

import com.dogmates.dogmates.core.match.domain.Match;
import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.db.match.MatchMap;
import com.google.cloud.firestore.FieldMask;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class FirestoreQueryHelper {
    private final Firestore firestore;

    private static String USER_PATH = "users/%s";

    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return firestore.collection("users")
                .get()
                .get()
                .toObjects(User.class);
    }

    public List<Match> getMatches(List<String> ids) throws ExecutionException, InterruptedException {
        if (ids.isEmpty()) {
            return List.of();
        } else {
            return firestore.collection("users")
                    .whereIn("id", ids)
                    .get()
                    .get()
                    .toObjects(Match.class);
        }

    }

    public MatchMap getUserMatchesList(String userId) throws ExecutionException, InterruptedException {
        return firestore.document(format(USER_PATH, userId))
                .get(FieldMask.of("matches"))
                .get()
                .toObject(MatchMap.class);
    }
}

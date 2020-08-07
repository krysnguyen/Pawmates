package com.dogmates.dogmates.db.walk;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.GetWalkPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class GetWalkAdapter implements GetWalkPort {
    private final Firestore firestore;

    private static String USER_PATH = "users/%s";
    private static String WALK_PATH = "users/%s/walks/%s";

    @Override
    public Walk getWalk(String userId, String walkId) throws ExecutionException, InterruptedException {
        val walkRef = firestore.document(format(WALK_PATH, userId, walkId))
                .get()
                .get();

        val walk = walkRef.toObject(Walk.class);
        walk.setId(walkRef.getId());

        return walk;
    }

    @Override
    public List<Walk> getMyWalks(String userId) throws ExecutionException, InterruptedException {
        return firestore.document(format(USER_PATH, userId))
                .collection("walks")
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(walkRef -> {
                    val walk = walkRef.toObject(Walk.class);
                    walk.setId(walkRef.getId());
                    return walk;
                })
                .collect(toList());
    }
}

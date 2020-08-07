package com.dogmates.dogmates.db.walk;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.GetWalkPort;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static java.time.LocalDate.now;
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
    public List<Walk> getMyRelatedWalks(String userId) throws ExecutionException, InterruptedException {
        val userRef = firestore.document(format(USER_PATH, userId));
        val user = userRef.get()
                .get()
                .toObject(User.class);
        val userMatchesIds = user.getMatches();

        val myWalks = getWalksLessThanSevenDays(userRef);

        if (!userMatchesIds.isEmpty()) {
            firestore.collection("users")
                    .whereIn("id", userMatchesIds)
                    .get()
                    .get()
                    .forEach(queryDocumentSnapshot -> {
                        val ref = queryDocumentSnapshot.getReference();
                        List<Walk> walks = null;
                        try {
                            walks = getWalksLessThanSevenDays(ref);
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        myWalks.addAll(walks);
                    });
        }

        return myWalks;
    }

    private List<Walk> getWalksLessThanSevenDays(DocumentReference userRef) throws ExecutionException, InterruptedException {
        return userRef.collection("walks")
                .whereGreaterThanOrEqualTo("expiryTimeStamp", now().toEpochDay())
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(this::createWalk)
                .filter(this::filterLessThanSevenDays)
                .collect(toList());
    }

    private Walk createWalk(QueryDocumentSnapshot walkRef) {
        val walk = walkRef.toObject(Walk.class);
        walk.setId(walkRef.getId());
        return walk;
    }

    private boolean filterLessThanSevenDays(Walk walk) {
        return walk.getDate() < now().plusDays(7).toEpochDay();
    }
}

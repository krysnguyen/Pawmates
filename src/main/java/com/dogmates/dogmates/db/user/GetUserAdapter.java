package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.db.common.FirestoreQueryHelper;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class GetUserAdapter implements GetUserPort {
    private final Firestore firestore;
    private final FirestoreQueryHelper firestoreQueryHelper;

    private static String USER_PATH = "users/%s";

    @Override
    public List<User> getAll() throws ExecutionException, InterruptedException {
        return firestore.collection("users")
                .get()
                .get()
                .toObjects(User.class);
    }

    @Override
    public User getUser(String id) throws ExecutionException, InterruptedException {
        return firestore.document(format(USER_PATH, id))
                .get()
                .get()
                .toObject(User.class);
    }

    @Override
    public List<User> getPotentialMatches(String userId) throws ExecutionException, InterruptedException {
        val user = getUser(userId);
        val allIds = firestoreQueryHelper.getAllUserIds();

        val filterBy = new HashSet<String>();
        makeFilteredIdSet(filterBy, user);

        val allIdsSet = new HashSet<String>();
        allIdsSet.addAll(allIds);

        allIdsSet.removeAll(filterBy);

        val ids = new ArrayList<>(allIdsSet);

        if (ids.isEmpty()) {
            return List.of();
        } else {
            return firestoreQueryHelper.getFilteredListOfUsers(ids);
        }
    }

    private void makeFilteredIdSet(HashSet<String> filterBy, User user) {
        filterBy.addAll(user.getLiked());
        filterBy.addAll(user.getDisliked());
        filterBy.addAll(user.getMatches());
        filterBy.add(user.getId());
    }
}

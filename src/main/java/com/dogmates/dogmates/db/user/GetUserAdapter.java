package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
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
    private static String USER_PATH = "users/%s";

    @Override
    public List<User> getAll() throws ExecutionException, InterruptedException {
        return firestore.collection("users")
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(user -> user.toObject(User.class))
                .collect(toList());
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
        val user = firestore.document(format(USER_PATH, userId))
                .get()
                .get()
                .toObject(User.class);

        val allIds = firestore.collection("users")
                .get()
                .get()
                .getDocuments()
                .stream()
                .map(user1 -> user1.toObject(User.class))
                .map(User::getId)
                .collect(toList());

        val filterBy = new HashSet<String>();
        filterBy.addAll(user.getLiked());
        filterBy.addAll(user.getDisliked());
        filterBy.addAll(user.getMatches());
        filterBy.add(userId);

        val allIdsSet = new HashSet<String>();
        allIdsSet.addAll(allIds);

        allIdsSet.removeAll(filterBy);

        val ids = new ArrayList<>(allIdsSet);

        if (ids.isEmpty()) {
            return List.of();
        } else {
            return firestore.collection("users")
                    .whereIn("id", ids)
                    .get()
                    .get()
                    .toObjects(User.class);
        }

    }
}

package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return firestore.document(format(USER_PATH,id))
                .get()
                .get()
                .toObject(User.class);
    }
}

package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.UpdateUserPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class UpdateUserAdapter implements UpdateUserPort {
    private final Firestore firestore;
    private static String USER_PATH = "users/%s";

    @Override
    public User update(User user) throws ExecutionException, InterruptedException {
        firestore.document(format(USER_PATH, user.getId()))
                .set(user);
        return firestore.document(format(USER_PATH, user.getId()))
                .get()
                .get()
                .toObject(User.class);
    }
}

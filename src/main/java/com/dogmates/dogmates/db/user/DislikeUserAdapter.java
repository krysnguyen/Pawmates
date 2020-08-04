package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.DislikeUserPort;
import com.google.cloud.firestore.FieldPath;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class DislikeUserAdapter implements DislikeUserPort {
    private final Firestore firestore;

    private static String USER_PATH = "users/%s";

    @Override
    public User dislike(User user, String dislikedUserId) throws ExecutionException, InterruptedException {
        user.getDisliked().add(dislikedUserId);
        val path = format(USER_PATH, user.getId());

        firestore.document(path)
                .update(FieldPath.of("disliked"), user.getDisliked());

        return firestore.document(path)
                .get()
                .get()
                .toObject(User.class);

    }
}

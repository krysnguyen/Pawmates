package com.dogmates.dogmates.db.walk;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.UpdateWalkPort;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class UpdateWalkAdapter implements UpdateWalkPort {
    private final Firestore firestore;

    private static String WALK_PATH = "users/%s/walks/%s";

    @Override
    public Walk join(String userId, String walkId, String joiningUserId) throws ExecutionException, InterruptedException {
        val path = format(WALK_PATH, userId, walkId);

        firestore.document(path)
                .update("joinedUsers", FieldValue.arrayUnion(joiningUserId));

        return firestore.document(path)
                .get()
                .get()
                .toObject(Walk.class);
    }
}

package com.dogmates.dogmates.db.walk;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.CreateWalkPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static org.mapstruct.factory.Mappers.getMapper;

@Component
@RequiredArgsConstructor
public class CreateWalkAdapter implements CreateWalkPort {
    private final Firestore firestore;
    private final WalkEntityMapper mapper = getMapper(WalkEntityMapper.class);

    private static String USER_PATH = "users/%s";

    @Override
    public Walk create(Walk walk, String userId) throws ExecutionException, InterruptedException {
        val entity = mapper.toEntity(walk);
        entity.setJoinedUsers(List.of());
        val userRef = firestore.document(format(USER_PATH, userId));
        val user = userRef.get()
                .get()
                .toObject(User.class);
        val walkRef = userRef
                .collection("walks")
                .add(entity)
                .get();


        val createdWalk = walkRef.get().get().toObject(Walk.class);
        createdWalk.setId(walkRef.getId());
        createdWalk.setFirstName(user.getFirstName());
        createdWalk.setLastName(user.getLastName());

        return createdWalk;
    }
}

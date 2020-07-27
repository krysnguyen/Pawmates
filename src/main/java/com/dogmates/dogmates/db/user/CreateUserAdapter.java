package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.CreateUserPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserPort {
    private final Firestore firestore;

    @Override
    public User create(User user) throws ExecutionException, InterruptedException {
        val users = firestore.collection("users");
        val ref = users.add(user);
        user.setId(ref.get().getId());
        return user;
    }
}

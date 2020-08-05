package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.CreateUserPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserPort {
    private final Firestore firestore;

    @Override
    public User create(User user) {
        firestore.collection("users").document(user.getId()).set(user);
        return user;
    }
}

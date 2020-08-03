package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.LikeUserPort;
import com.google.cloud.firestore.Firestore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeUserAdapter implements LikeUserPort {
    private final Firestore firestore;

    @Override
    public User like(String userId, String likedUserId) {
        return null;
    }
}

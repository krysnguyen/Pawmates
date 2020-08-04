package com.dogmates.dogmates.db.user;

import com.dogmates.dogmates.core.user.domain.User;
import com.google.cloud.firestore.DocumentReference;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {
    User toUser(DocumentReference documentReference);
}

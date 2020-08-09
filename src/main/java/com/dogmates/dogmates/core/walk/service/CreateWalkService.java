package com.dogmates.dogmates.core.walk.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.port.CreateWalkPort;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkCmd;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import static java.time.ZoneOffset.UTC;
import static org.mapstruct.factory.Mappers.getMapper;

@Service
@RequiredArgsConstructor
public class CreateWalkService implements CreateWalkUseCase {
    private final CreateWalkPort createWalkPort;

    private final WalkMapper mapper = getMapper(WalkMapper.class);

    @Override
    public Walk create(CreateWalkCmd cmd, String userId) throws ExecutionException, InterruptedException {
        val walk = mapper.toWalk(cmd);
        val date = cmd.getDate().toEpochDay();
        val time = cmd.getTime().toNanoOfDay();
        val expiryTime = cmd.getTime().plusMinutes(cmd.getDuration());
        val expiryTimeStamp = LocalDateTime.of(cmd.getDate(), expiryTime);

        walk.setDate(date);
        walk.setTime(time);
        walk.setUserId(userId);
        walk.setExpiryTimeStamp(expiryTimeStamp.toEpochSecond(UTC));

        return createWalkPort.create(walk, userId);
    }
}

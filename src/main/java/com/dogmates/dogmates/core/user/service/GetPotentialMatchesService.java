package com.dogmates.dogmates.core.user.service;

import com.dogmates.dogmates.core.user.domain.User;
import com.dogmates.dogmates.core.user.port.GetUserPort;
import com.dogmates.dogmates.core.user.usecase.read.GetPotentialMatchesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetPotentialMatchesService implements GetPotentialMatchesUseCase {
    private final GetUserPort getUserPort;


    @Override
    public List<User> getPotentialMatches(String userId) throws ExecutionException, InterruptedException {
        return getUserPort.getPotentialMatches(userId);
    }
}

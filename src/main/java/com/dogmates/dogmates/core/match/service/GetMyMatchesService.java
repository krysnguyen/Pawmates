package com.dogmates.dogmates.core.match.service;

import com.dogmates.dogmates.core.match.domain.Match;
import com.dogmates.dogmates.core.match.port.GetMatchPort;
import com.dogmates.dogmates.core.match.usecase.GetMyMatchesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GetMyMatchesService implements GetMyMatchesUseCase {
    private final GetMatchPort getMatchPort;

    @Override
    public List<Match> getMyMatches(String userId) throws ExecutionException, InterruptedException {
        return getMatchPort.getMyMatches(userId);
    }
}

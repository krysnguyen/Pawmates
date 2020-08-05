package com.dogmates.dogmates.rest.match;

import com.dogmates.dogmates.core.match.usecase.GetMyMatchesUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(MatchController.API_MATCH_PATH)
public class MatchController {
    static final String API_MATCH_PATH = "/api/v1/matches/{userId}";

    private final GetMyMatchesUseCase getMyMatchesUseCase;
    private final MatchModelAssembler matchModelAssembler;

    @GetMapping
    public ResponseEntity<List<MatchModel>> getMyMatches(@PathVariable("userId") String userId) throws ExecutionException, InterruptedException {
        val matches = getMyMatchesUseCase.getMyMatches(userId);
        val models = matchModelAssembler.toModelsWithLinks(matches);
        return new ResponseEntity<>(models, OK);
    }

}

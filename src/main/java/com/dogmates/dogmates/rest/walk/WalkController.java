package com.dogmates.dogmates.rest.walk;

import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkCmd;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkUseCase;
import com.dogmates.dogmates.core.walk.usecase.read.GetMyWalksUseCase;
import com.dogmates.dogmates.core.walk.usecase.read.GetWalkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(WalkController.API_WALK_PATH)
public class WalkController {
    static final String API_WALK_PATH = "/api/v1/users/{userId}/walks";

    private final CreateWalkUseCase createWalkUseCase;
    private final GetWalkUseCase getWalkUseCase;
    private final GetMyWalksUseCase getMyWalksUseCase;
    private final WalkModelAssembler walkModelAssembler;

    @PostMapping
    public ResponseEntity<WalkModel> createWalk(@PathVariable("userId") String userId, @RequestBody @Valid CreateWalkCmd cmd) throws ExecutionException, InterruptedException {
        val walk = createWalkUseCase.create(cmd, userId);
        val model = walkModelAssembler.toModelWithLinks(walk);

        return new ResponseEntity<>(model, OK);
    }

    @GetMapping("/{walkId}")
    public ResponseEntity<WalkModel> getWalk(@PathVariable("userId") String userId, @PathVariable("walkId") String walkId) throws ExecutionException, InterruptedException {
        val walk = getWalkUseCase.get(userId, walkId);
        val model = walkModelAssembler.toModelWithLinks(walk);

        return new ResponseEntity<>(model, OK);
    }

    @GetMapping
    public ResponseEntity<WalksModel> getMyWalks(@PathVariable("userId") String userId) throws ExecutionException, InterruptedException {
        val walks = getMyWalksUseCase.getMyWalks(userId);
        val models = walkModelAssembler.toModelsWithLinks(walks);
        val model = WalksModel.builder()
                .currentWalks(models)
                .build();
        return new ResponseEntity<>(model, OK);
    }
}

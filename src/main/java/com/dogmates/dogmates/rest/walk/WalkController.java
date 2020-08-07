package com.dogmates.dogmates.rest.walk;

import com.dogmates.dogmates.core.user.usecase.update.IdCmd;
import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkCmd;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkUseCase;
import com.dogmates.dogmates.core.walk.usecase.read.GetMyRelatedWalks;
import com.dogmates.dogmates.core.walk.usecase.read.GetWalkUseCase;
import com.dogmates.dogmates.core.walk.usecase.update.JoinWalkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(WalkController.API_WALK_PATH)
public class WalkController {
    static final String API_WALK_PATH = "/api/v1/users/{userId}/walks";

    private final CreateWalkUseCase createWalkUseCase;
    private final GetWalkUseCase getWalkUseCase;
    private final JoinWalkUseCase joinWalkUseCase;

    private final GetMyRelatedWalks getMyRelatedWalks;
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
        val allWalks = getMyRelatedWalks.getWalks(userId);

        val myWalks = allWalks.stream()
                .filter(walk -> walk.getUserId().equals(userId) || walk.getJoinedUsers().contains(userId))
                .collect(toList());

        val currentWalks = myWalks.stream()
                .filter(this::filterOutFutureWalks)
                .collect(toList());

        val futureWalks = myWalks.stream()
                .filter(this::filterOutCurrentWalks)
                .collect(toList());

        val myMatchesWalks = allWalks.stream()
                .filter(walk -> !walk.getUserId().equals(userId) && !walk.getJoinedUsers().contains(userId))
                .collect(toList());

        val currentWalkModels = walkModelAssembler.toModelsWithLinks(currentWalks);
        val futureWalkModels = walkModelAssembler.toModelsWithLinks(futureWalks);
        val myMatchesWalkModels = walkModelAssembler.toModelsWithLinks(myMatchesWalks);


        val model = WalksModel.builder()
                .currentWalks(currentWalkModels)
                .myFutureWalks(futureWalkModels)
                .myMatchesWalks(myMatchesWalkModels)
                .build();

        return new ResponseEntity<>(model, OK);
    }

    @PutMapping("/{walkId}/join")
    public ResponseEntity<WalkModel> joinWalk(@PathVariable("userId") String userId, @PathVariable("walkId") String walkId, @RequestBody @Valid IdCmd cmd) throws ExecutionException, InterruptedException {
        val walk = joinWalkUseCase.join(userId, walkId, cmd.getId());
        val model = walkModelAssembler.toModelWithLinks(walk);

        return new ResponseEntity<>(model, OK);
    }

    private boolean filterOutCurrentWalks(Walk walk) {
        return LocalDateTime.of(LocalDate.ofEpochDay(walk.getDate()), LocalTime.ofNanoOfDay(walk.getTime())).compareTo(LocalDateTime.now()) > 0;
    }

    private boolean filterOutFutureWalks(Walk walk) {
        val localDateTime = LocalDateTime.of(LocalDate.ofEpochDay(walk.getDate()), LocalTime.ofNanoOfDay(walk.getTime()));
        val nowOrAfter = localDateTime.compareTo(LocalDateTime.now()) <= 0;
        val durationGreaterThanNow = localDateTime.plusMinutes(walk.getTime()).compareTo(LocalDateTime.now()) > 0;
        return nowOrAfter && durationGreaterThanNow;

    }
}

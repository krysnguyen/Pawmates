package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserUseCase;
import com.dogmates.dogmates.core.user.usecase.read.GetPotentialMatchesUseCase;
import com.dogmates.dogmates.core.user.usecase.read.GetUserUseCase;
import com.dogmates.dogmates.core.user.usecase.read.GetUsersUseCase;
import com.dogmates.dogmates.core.user.usecase.update.DislikeUserUseCase;
import com.dogmates.dogmates.core.user.usecase.update.IdCmd;
import com.dogmates.dogmates.core.user.usecase.update.LikeUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.API_USER_PATH)
public class UserController {
    static final String API_USER_PATH = "/api/v1/users";

    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetPotentialMatchesUseCase getPotentialMatchesUseCase;
    private final LikeUserUseCase likeUserUseCase;
    private final DislikeUserUseCase dislikeUserUseCase;

    private final UserModelAssembler userModelAssembler;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid CreateUserCmd cmd) throws ExecutionException, InterruptedException {
        val user = createUserUseCase.create(cmd);
        val model = userModelAssembler.toModel(user);

        return new ResponseEntity<>(model, OK);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers() throws ExecutionException, InterruptedException {
        val users = getUsersUseCase.getUsers();
        val models = userModelAssembler.toModelsWithLinks(users);

        return new ResponseEntity<>(models, OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getPotentialMatches(@PathVariable("userId") String userId) throws ExecutionException, InterruptedException {
        val user = getUserUseCase.getUser(userId);
        val model = userModelAssembler.toModelWithLinks(user);

        return new ResponseEntity<>(model, OK);
    }

    @GetMapping("/{userId}/potential")
    public ResponseEntity<List<UserModel>> getUser(@PathVariable("userId") String userId) throws ExecutionException, InterruptedException {
        val users = getPotentialMatchesUseCase.getPotentialMatches(userId);
        val models = userModelAssembler.toModelsWithLinks(users);
        return new ResponseEntity<>(models, OK);
    }

    @PutMapping("/{userId}/likes")
    public ResponseEntity<UserModel> likeUser(@PathVariable("userId") String userId, @RequestBody @Valid IdCmd cmd) throws ExecutionException, InterruptedException {
        val user = likeUserUseCase.like(userId, cmd);
        val model = userModelAssembler.toModelWithLinks(user);
        return new ResponseEntity<>(model, OK);
    }

    @PutMapping("/{userId}/dislikes")
    public ResponseEntity<UserModel> dislikeUser(@PathVariable("userId") String userId, @RequestBody @Valid IdCmd cmd) throws ExecutionException, InterruptedException {
        val user = dislikeUserUseCase.dislike(userId, cmd);
        val model = userModelAssembler.toModelWithLinks(user);
        return new ResponseEntity<>(model, OK);
    }

}

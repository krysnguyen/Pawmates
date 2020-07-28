package com.dogmates.dogmates.rest;

import com.dogmates.dogmates.core.user.usecase.create.CreateUserCmd;
import com.dogmates.dogmates.core.user.usecase.create.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.API_USER_PATH)
public class UserController {
    static final String API_USER_PATH = "/api/v1/users";

    private final CreateUserUseCase createUserUseCase;

    private final UserModelAssembler userModelAssembler;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid CreateUserCmd cmd) throws ExecutionException, InterruptedException {
        val user = createUserUseCase.create(cmd);
        val model = userModelAssembler.toModel(user);

        return new ResponseEntity<>(model, OK);
    }

}

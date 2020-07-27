package com.dogmates.dogmates.core.user.usecase.create;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface CreateUserUseCase {
    User create(CreateUserCmd cmd) throws ExecutionException, InterruptedException;
}

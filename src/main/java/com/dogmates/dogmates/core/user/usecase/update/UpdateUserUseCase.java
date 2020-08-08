package com.dogmates.dogmates.core.user.usecase.update;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface UpdateUserUseCase {
    User update(UpdateUserCmd cmd, String userId) throws ExecutionException, InterruptedException;
}

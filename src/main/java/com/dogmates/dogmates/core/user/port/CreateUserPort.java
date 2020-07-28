package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface CreateUserPort {
    User create(User user) throws ExecutionException, InterruptedException;
}

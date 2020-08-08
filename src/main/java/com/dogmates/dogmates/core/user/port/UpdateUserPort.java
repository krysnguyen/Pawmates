package com.dogmates.dogmates.core.user.port;

import com.dogmates.dogmates.core.user.domain.User;

import java.util.concurrent.ExecutionException;

public interface UpdateUserPort {
    User update(User user) throws ExecutionException, InterruptedException;
}

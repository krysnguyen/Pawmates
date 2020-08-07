package com.dogmates.dogmates.core.walk.usecase.read;

import com.dogmates.dogmates.core.walk.domain.Walk;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GetMyRelatedWalks {
    List<Walk> getWalks(String userId) throws ExecutionException, InterruptedException;
}

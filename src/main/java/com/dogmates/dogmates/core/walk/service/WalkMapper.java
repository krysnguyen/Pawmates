package com.dogmates.dogmates.core.walk.service;

import com.dogmates.dogmates.core.walk.domain.Walk;
import com.dogmates.dogmates.core.walk.usecase.create.CreateWalkCmd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WalkMapper {
    @Mapping(source = "date", target = "date", ignore = true)
    @Mapping(source = "time", target = "time", ignore = true)
    Walk toWalk(CreateWalkCmd cmd);
}

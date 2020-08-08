package com.dogmates.dogmates.rest.walk;

import com.dogmates.dogmates.core.walk.domain.Walk;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface WalkModelMapper {
    @Mapping(source = "date", target = "date", ignore = true)
    @Mapping(source = "time", target = "time", ignore = true)
    WalkModel toModel(Walk walk);
}

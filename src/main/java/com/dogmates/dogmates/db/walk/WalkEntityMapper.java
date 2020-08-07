package com.dogmates.dogmates.db.walk;

import com.dogmates.dogmates.core.walk.domain.Walk;
import org.mapstruct.Mapper;

@Mapper
public interface WalkEntityMapper {
    WalkEntity toEntity(Walk walk);
}

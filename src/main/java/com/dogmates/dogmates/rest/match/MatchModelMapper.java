package com.dogmates.dogmates.rest.match;

import com.dogmates.dogmates.core.match.domain.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MatchModelMapper {
    @Mapping(source = "id", target = "userId")
    MatchModel toModel(Match match);
}

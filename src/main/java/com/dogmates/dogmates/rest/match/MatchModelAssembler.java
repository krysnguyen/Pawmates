package com.dogmates.dogmates.rest.match;

import com.dogmates.dogmates.core.match.domain.Match;
import com.dogmates.dogmates.rest.user.UserController;
import lombok.val;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MatchModelAssembler extends RepresentationModelAssemblerSupport<Match, MatchModel> {
    private final MatchModelMapper mapper = getMapper(MatchModelMapper.class);

    public MatchModelAssembler() {
        super(MatchController.class, MatchModel.class);
    }

    @Override
    public MatchModel toModel(Match match) {
        return mapper.toModel(match);
    }

    public MatchModel toModelWithLinks(Match match) throws ExecutionException, InterruptedException {
        val model = toModel(match);
        val selfLink = linkTo(methodOn(UserController.class).getUser(match.getId())).withSelfRel();
        model.add(selfLink);
        return model;
    }

    public List<MatchModel> toModelsWithLinks(List<Match> matches) throws ExecutionException, InterruptedException {
        List<MatchModel> list = new ArrayList<>();
        for (Match match : matches) {
            MatchModel matchModel = toModelWithLinks(match);
            list.add(matchModel);
        }
        return list;
    }
}

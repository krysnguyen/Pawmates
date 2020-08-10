package com.dogmates.dogmates.rest.walk;

import com.dogmates.dogmates.core.walk.domain.Walk;
import lombok.val;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mapstruct.factory.Mappers.getMapper;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WalkModelAssembler extends RepresentationModelAssemblerSupport<Walk, WalkModel> {
    private final WalkModelMapper mapper = getMapper(WalkModelMapper.class);

    public WalkModelAssembler() {
        super(WalkController.class, WalkModel.class);
    }

    @Override
    public WalkModel toModel(Walk walk) {
        val model = mapper.toModel(walk);
        val date = LocalDate.ofEpochDay(walk.getDate());
        val time = LocalTime.ofNanoOfDay(walk.getTime());
        model.setDate(date);
        model.setTime(time);
        val userModel = new WalkModel.UserModel();
        userModel.setUserId(walk.getUserId());
        userModel.setFirstName(walk.getFirstName());
        userModel.setLastName(walk.getLastName());
        userModel.setImages(walk.getImages());
        model.setUser(userModel);

        return model;
    }

    public WalkModel toModelWithLinks(Walk walk) throws ExecutionException, InterruptedException {
        val model = toModel(walk);
        val selfLink = linkTo(methodOn(WalkController.class).getWalk(walk.getUserId(), walk.getId())).withSelfRel();
        model.add(selfLink);
        return model;
    }

    public List<WalkModel> toModelsWithLinks(List<Walk> walks) throws ExecutionException, InterruptedException {
        List<WalkModel> list = new ArrayList<>();
        for (Walk walk : walks) {
            val model = toModelWithLinks(walk);
            list.add(model);
        }
        return list;
    }
}

package com.dogmates.dogmates.rest.user;

import com.dogmates.dogmates.core.user.domain.User;
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
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {
    private final UserModelMapper mapper = getMapper(UserModelMapper.class);

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user) {
        return mapper.toModel(user);
    }

    public UserModel toModelWithLinks(User user) throws ExecutionException, InterruptedException {
        val model = toModel(user);
        val selfLink = linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel();
        model.add(selfLink);
        return model;
    }

    public List<UserModel> toModelsWithLinks(List<User> users) throws ExecutionException, InterruptedException {
        List<UserModel> list = new ArrayList<>();
        for (User user : users) {
            UserModel userModel = toModelWithLinks(user);
            list.add(userModel);
        }
        return list;
    }
}

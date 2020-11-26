package com.teammatch.tournament.controller;

import com.teammatch.tournament.domain.service.DefaultUserDetailsService;
import com.teammatch.tournament.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @GetMapping
    public List<UserResource> getAll() {
        return userDetailsService.getAll().stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
    }

    private UserResource convertToResource(User user) {
        return mapper.map(user, UserResource.class);
    }

}

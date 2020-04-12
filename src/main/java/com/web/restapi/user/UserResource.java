package com.web.restapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    //GET /users

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    // retrive User by id

    //
}

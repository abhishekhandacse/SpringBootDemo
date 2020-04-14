package com.web.restapi.user;

import com.web.restapi.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// Static Import
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public CollectionModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null) throw new UserNotFoundException("Requested User Not found " + id);
        List<User> allusers=new ArrayList<>();
        allusers.add(user);
        // HATEOAS implementation
        Link link=linkTo(methodOn(UserResource.class).retrieveAllUsers()).withRel("all-users");
        CollectionModel<User> result=new CollectionModel<>(allusers,link);

        return result;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser=service.save(user);
        //Send Created Status Back
        //Building path like   /users/{id}
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user=service.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("Request User Not Found " + id);
    }
}

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
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// Static Import

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public CollectionModel<Optional<User>> retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new UserNotFoundException("Requested User Not found " + id);

        List<Optional<User>> allusers=new ArrayList<>();
        allusers.add(user);
        // HATEOAS implementation
        Link link=linkTo(methodOn(UserJPAResource.class).retrieveAllUsers()).withRel("all-users");
        CollectionModel<Optional<User>> result=new CollectionModel<>(allusers,link);
        return result;
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser=userRepository.save(user);
        //Send Created Status Back
        //Building path like   /users/{id}
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        if(!userRepository.findById(id).isPresent())throw new UserNotFoundException("Requested User to be deleted Not found " + id);
        userRepository.deleteById(id);
    }
}

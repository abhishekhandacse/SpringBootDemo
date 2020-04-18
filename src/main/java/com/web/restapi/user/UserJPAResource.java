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
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
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
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id)
    {
        Optional<User> byId = userRepository.findById(id);
        // Checking weather user exists or not
        if(!byId.isPresent()){
            throw new UserNotFoundException("id"+id);
        }
        return byId.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> byId = userRepository.findById(id);
        // Checking weather user exists or not
        if(!byId.isPresent()){
            throw new UserNotFoundException("id"+id);
        }
        User user = byId.get();

        post.setUser(user);

        Post save = postRepository.save(post);

        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}

package controllers;

import domain.User;
import firebase.FirebaseSecurity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.UserRepository;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "User Controller", description = "Controller to manage users, they can login with Firebase")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public HttpEntity<Boolean> createUser(@RequestBody User newUser) {
        boolean userCreationSuccess = userRepository.createUser(newUser);

        if(userCreationSuccess) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @FirebaseSecurity
    @GetMapping(value = "/{uid}", produces = "application/json")
    public @ResponseBody HttpEntity<User> getUserByUid(@PathVariable String uid) {
        User user = userRepository.getUserByUid(uid);

        if(user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

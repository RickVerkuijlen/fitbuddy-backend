package controllers;

import firebase.FirebaseSecurity;
import domain.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ActivityRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping(value = "/user/{uid}", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<Activity>> getAllActivitiesFromUser(@PathVariable String uid) {
        System.out.println("[" + LocalDateTime.now() + "] getAllActivitiesFromUser");

        return new ResponseEntity<>(activityRepository.getAllActivitiesFromUser(uid), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<Activity> getActivityById(@PathVariable String id) {
        System.out.println("[" + LocalDateTime.now() + "] getActivityById");
        return new ResponseEntity<>(activityRepository.getActivityById(id), HttpStatus.OK);
    }

    @FirebaseSecurity
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> postActivity(@RequestBody Activity dto) {
        System.out.println("[" + LocalDateTime.now() + "] postActivity");
        System.out.println(dto);
        boolean userCreationSuccess = activityRepository.createActivity(dto);

        if(userCreationSuccess) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

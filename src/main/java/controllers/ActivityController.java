package controllers;

import domain.Activity;
import firebase.FirebaseSecurity;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ActivityRepository;

import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Activity Controller", description = "Controller to manage the users activities with different sports")
public class ActivityController {

    private ActivityRepository activityRepository;

    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping(value = "/user/{uid}", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<Activity>> getAllActivitiesFromUser(@PathVariable String uid) {
        log.info("getAllActivitiesFromUser");

        return new ResponseEntity<>(activityRepository.getAllActivitiesFromUser(uid), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<Activity> getActivityById(@PathVariable String id) {
        log.info("getActivityById");
        return new ResponseEntity<>(activityRepository.getActivityById(id), HttpStatus.OK);
    }

    @FirebaseSecurity
    @PostMapping(consumes = "application/json", produces = "application/json")
    public @ResponseBody
    HttpEntity<Boolean> postActivity(@RequestBody Activity activity) {
        log.info("postActivity");
        boolean userCreationSuccess = activityRepository.createActivity(activity);

        if(userCreationSuccess) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

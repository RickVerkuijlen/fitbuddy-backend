package controllers;

import firebase.FirebaseSecurity;
import domain.SubscribedSport;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.SubscribedSportRepository;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/subscribedsport")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Subscribed Sports Controller", description = "Controller to manage the subscribed sports of an user")
public class SubscribedSportController {

    private SubscribedSportRepository subscribedSportRepository;

    private static final Logger log = LoggerFactory.getLogger(SubscribedSportController.class);

    @Autowired
    public SubscribedSportController(SubscribedSportRepository subscribedSportRepository) {
        this.subscribedSportRepository = subscribedSportRepository;
    }

    @GetMapping(value = "/{uid}", produces = "application/json")
    public @ResponseBody
    HttpEntity<SubscribedSport> getSubscribedSportsFromUserByUid(@PathVariable String uid) {
        log.info("getSubscribedSportsFromUserByUid");
        return new ResponseEntity<>(subscribedSportRepository.getSubscribedSportsFromUserByUid(uid), HttpStatus.OK);
    }

    @FirebaseSecurity
    @PostMapping(value = "/subscribe", consumes = "application/json", produces = "application/json")
    public HttpEntity<Object> subscribeToSport(@RequestBody SubscribedSport subscribedSport) {
        log.info("subscribeToSport");

        boolean subscriptionSuccess = subscribedSportRepository.subscribeUserToSport(subscribedSport);

        if(subscriptionSuccess) {
            return new ResponseEntity<>("User subscribed to sport", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/unsubscribe/{userId}/{sportId}")
    public HttpEntity<Object> unsubscribeFromSport(@PathVariable("userId") String userId,
                                                   @PathVariable("sportId") int sportId) {
        log.info("unsubscribeFromSport");

        SubscribedSport dto = new SubscribedSport();
        dto.setUserUid(userId);
        dto.setSportId(sportId);

        boolean subscriptionSuccess = subscribedSportRepository.unsubscribeUserFromSport(dto);

        if(subscriptionSuccess) {
            return new ResponseEntity<>("User unsubscribed from sport", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

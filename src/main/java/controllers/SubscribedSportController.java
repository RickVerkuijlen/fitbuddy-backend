package controllers;

import firebase.FirebaseSecurity;
import objects.SubscribedSportDTO;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.SubscribedSportRepository;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/subscribedsport")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscribedSportController {

    private SubscribedSportRepository subscribedSportRepository;

    @Autowired
    public SubscribedSportController(SubscribedSportRepository subscribedSportRepository) {
        this.subscribedSportRepository = subscribedSportRepository;
    }

    @GetMapping(value = "/{uid}", produces = "application/json")
    public @ResponseBody
    HttpEntity<SubscribedSportDTO> getSubscribedSportsFromUserByUid(@PathVariable String uid) {
        System.out.println("[" + LocalDateTime.now() + "] getSubscribedSportsFromUserByUid");
        return new ResponseEntity<>(subscribedSportRepository.getSubscribedSportsFromUserByUid(uid), HttpStatus.OK);
    }

    @FirebaseSecurity
    @PostMapping(value = "/subscribe", consumes = "application/json", produces = "application/json")
    public HttpEntity<Object> subscribeToSport(@RequestBody SubscribedSportDTO subscribedSportDTO) {
        System.out.println("[" + LocalDateTime.now() + "] subscribeToSport");

        boolean subscriptionSuccess = subscribedSportRepository.subscribeUserToSport(subscribedSportDTO);

        if(subscriptionSuccess) {
            return new ResponseEntity<>("User subscribed to sport", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/unsubscribe/{userId}/{sportId}")
    public HttpEntity<Object> unsubscribeFromSport(@PathVariable("userId") String userId,
                                                   @PathVariable("sportId") int sportId) {
        System.out.println("[" + LocalDateTime.now() + "] unsubscribeFromSport");

        SubscribedSportDTO dto = new SubscribedSportDTO();
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

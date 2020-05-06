package repositories;

import context.SportContext;
import context.SubscribedSportContext;
import controllers.SubscribedSportController;
import controllers.UserController;
import controllers.sports.SportController;
import objects.SubscribedSportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscribedSportRepository {

    private SubscribedSportContext subscribedSportContext;

    @Autowired
    public SubscribedSportRepository(SubscribedSportContext subscribedSportContext) {
        this.subscribedSportContext = subscribedSportContext;
    }

    public SubscribedSportDTO getSubscribedSportsFromUserByUid(String uid) {
        List<SubscribedSportDTO> sports = subscribedSportContext.getSubscribedSportsFromUserByUid(uid);
        SubscribedSportDTO result = new SubscribedSportDTO();
        Link selfLink = linkTo(methodOn(SubscribedSportController.class).getSubscribedSportsFromUserByUid(uid)).withSelfRel();
        Link userLink = linkTo(methodOn(UserController.class).getUserByUid(uid)).withRel("user");
        result.add(selfLink);
        result.add(userLink);
        for (SubscribedSportDTO sport :
                sports) {
            Link sportLink = linkTo(methodOn(SportController.class).getSportById(String.valueOf(sport.getSportId()))).withRel("sport");
            result.add(sportLink);
        }
        return result;
    }

    public boolean subscribeUserToSport(SubscribedSportDTO dto) {
        return subscribedSportContext.create(dto);
    }

    public boolean unsubscribeUserFromSport(SubscribedSportDTO dto) {
        return subscribedSportContext.delete(dto);
    }
}

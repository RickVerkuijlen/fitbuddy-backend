package repositories;

import context.SubscribedSportContext;
import controllers.SubscribedSportController;
import controllers.UserController;
import controllers.sports.SportController;
import domain.SubscribedSport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class SubscribedSportRepository {

    private SubscribedSportContext subscribedSportContext;

    @Autowired
    public SubscribedSportRepository(SubscribedSportContext subscribedSportContext) {
        this.subscribedSportContext = subscribedSportContext;
    }

    public SubscribedSport getSubscribedSportsFromUserByUid(String uid) {
        List<SubscribedSport> sports = subscribedSportContext.getSubscribedSportsFromUserByUid(uid);
        SubscribedSport result = new SubscribedSport();
        Link selfLink = linkTo(methodOn(SubscribedSportController.class).getSubscribedSportsFromUserByUid(uid)).withSelfRel();
        Link userLink = linkTo(methodOn(UserController.class).getUserByUid(uid)).withRel("user");
        result.add(selfLink);
        result.add(userLink);
        for (SubscribedSport sport :
                sports) {
            Link sportLink = linkTo(methodOn(SportController.class).getSportById(String.valueOf(sport.getSportId()))).withRel("sport");
            result.add(sportLink);
        }
        return result;
    }

    public boolean subscribeUserToSport(SubscribedSport dto) {
        return subscribedSportContext.create(dto);
    }

    public boolean unsubscribeUserFromSport(SubscribedSport dto) {
        return subscribedSportContext.delete(dto);
    }
}

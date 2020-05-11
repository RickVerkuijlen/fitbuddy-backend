package repositories;

import context.interfaces.IUserContext;
import controllers.SubscribedSportController;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class UserRepository {
    private IUserContext userContext;

    @Autowired
    public UserRepository(IUserContext userContext) {
        this.userContext = userContext;
    }

    public boolean createUser(User user) {
        return userContext.create(user);
    }

    public User getUserByUid(String uid) {
        User user = userContext.getUserById(uid);

        Link subscribedSports = linkTo(methodOn(SubscribedSportController.class).getSubscribedSportsFromUserByUid(uid)).withRel("subscribedSports");
        user.add(subscribedSports);

        return user;
    }
}

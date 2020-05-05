package repositories;

import context.interfaces.IUserContext;
import controllers.SubscribedSportController;
import objects.UserDTO;
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

    public boolean createUser(UserDTO userDTO) {
        return userContext.create(userDTO);
    }

    public UserDTO getUserByUid(String uid) {
        UserDTO userDTO = userContext.getUserById(uid);

        Link subscribedSports = linkTo(methodOn(SubscribedSportController.class).getSubscribedSportsFromUserByUid(uid)).withRel("subscribedSports");
        userDTO.add(subscribedSports);

        return userDTO;
    }
}

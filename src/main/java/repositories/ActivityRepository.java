package repositories;

import context.ActivityContext;
import controllers.ActivityController;
import controllers.UserController;
import controllers.sports.SportController;
import objects.ActivityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityRepository {
    private ActivityContext activityContext;

    @Autowired
    public ActivityRepository(ActivityContext activityContext) {
        this.activityContext = activityContext;
    }

    public List<ActivityDTO> getAllActivitiesFromUser(String uid) {
        List<ActivityDTO> result = new ArrayList<>();
        for (ActivityDTO activity : activityContext.getAllActivitiesFromUser(uid)) {

            setLinks(activity);

            result.add(activity);
        }
        return result;
    }

    public ActivityDTO getActivityById(String id) {
        ActivityDTO activityDTO = activityContext.getActivityById(id);
        setLinks(activityDTO);
        return activityDTO;
    }

    public boolean createActivity(ActivityDTO dto) {
        return activityContext.create(dto);
    }

    private void setLinks(ActivityDTO activityDTO) {
        Link selfLink = linkTo(methodOn(ActivityController.class).getActivityById(String.valueOf(activityDTO.getActivityId()))).withSelfRel();
        Link sportLink = linkTo(methodOn(SportController.class).getSportById(String.valueOf(activityDTO.getSportId()))).withRel("sport");
        Link userLink = linkTo(methodOn(UserController.class).getUserByUid(String.valueOf(activityDTO.getUserId()))).withRel("user");
        activityDTO.add(selfLink, sportLink, userLink);
    }
}

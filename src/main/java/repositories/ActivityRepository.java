package repositories;

import context.ActivityContext;
import controllers.ActivityController;
import controllers.UserController;
import controllers.sports.SportController;
import domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ActivityRepository {
    private ActivityContext activityContext;

    @Autowired
    public ActivityRepository(ActivityContext activityContext) {
        this.activityContext = activityContext;
    }

    public List<Activity> getAllActivitiesFromUser(String uid) {
        List<Activity> result = new ArrayList<>();
        for (Activity activity : activityContext.getAllActivitiesFromUser(uid)) {

            setLinks(activity);

            result.add(activity);
        }
        return result;
    }

    public Activity getActivityById(String id) {
        Activity activity = activityContext.getActivityById(id);
        setLinks(activity);
        return activity;
    }

    public boolean createActivity(Activity dto) {
        return activityContext.create(dto);
    }

    private void setLinks(Activity activity) {
        Link selfLink = linkTo(methodOn(ActivityController.class).getActivityById(String.valueOf(activity.getActivityId()))).withSelfRel();
        Link sportLink = linkTo(methodOn(SportController.class).getSportById(String.valueOf(activity.getSportId()))).withRel("sport");
        Link userLink = linkTo(methodOn(UserController.class).getUserByUid(String.valueOf(activity.getUserId()))).withRel("user");
        activity.add(selfLink, sportLink, userLink);
    }
}

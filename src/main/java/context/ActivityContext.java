package context;

import context.interfaces.CrudOperations;
import domain.Activity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityContext extends CrudOperations<Activity> {

    public List<Activity> getAllActivitiesFromUser(String uid) {
        List<Activity> result = new ArrayList<>();
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query<Activity> query = session.createQuery("from Activity a where a.userId = :user_id order by date desc ", Activity.class);
            query.setParameter("user_id", uid);
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public Activity getActivityById(String id) {
        Activity result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Activity a where a.id = :id", Activity.class);
            query.setParameter("id", Integer.parseInt(id));
            result = (Activity)query.uniqueResult();
        }
        return result;
    }
}

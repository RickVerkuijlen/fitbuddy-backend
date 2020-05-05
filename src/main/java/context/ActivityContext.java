package context;

import objects.ActivityDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityContext {

    public List<ActivityDTO> getAllActivitiesFromUser(String uid) {
        List<ActivityDTO> result = new ArrayList<>();
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query<ActivityDTO> query = session.createQuery("from ActivityDTO a where a.userId = :user_id order by date desc ", ActivityDTO.class);
            query.setParameter("user_id", uid);
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public ActivityDTO getActivityById(String id) {
        ActivityDTO result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from ActivityDTO a where a.id = :id", ActivityDTO.class);
            query.setParameter("id", Integer.parseInt(id));
            result = (ActivityDTO)query.uniqueResult();
        }
        return result;
    }
}

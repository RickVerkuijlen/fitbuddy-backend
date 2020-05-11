package context;

import context.interfaces.CrudOperations;
import objects.SubscribedSportDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscribedSportContext extends CrudOperations<SubscribedSportDTO> {

    public List<SubscribedSportDTO> getSubscribedSportsFromUserByUid(String uid) {
        List<SubscribedSportDTO> result = new ArrayList<>();
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query<SubscribedSportDTO> query = session.createQuery("from SubscribedSportDTO s where s.userUid = :user_id", SubscribedSportDTO.class);
            query.setParameter("user_id", uid);
            result = query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}

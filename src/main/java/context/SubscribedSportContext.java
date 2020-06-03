package context;

import context.interfaces.CrudOperations;
import domain.SubscribedSport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubscribedSportContext extends CrudOperations<SubscribedSport> {

    private static final Logger log = LoggerFactory.getLogger(SubscribedSportContext.class);

    public List<SubscribedSport> getSubscribedSportsFromUserByUid(String uid) {
        List<SubscribedSport> result = new ArrayList<>();
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query<SubscribedSport> query = session.createQuery("from SubscribedSport s where s.userUid = :user_id", SubscribedSport.class);
            query.setParameter("user_id", uid);
            result = query.list();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

}

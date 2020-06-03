package context;

import domain.Sport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.List;

@Component
public class SportContext {



    public Sport getSportById(int id) {
        Sport result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Sport s where s.id = :sport_id", Sport.class);
            query.setParameter("sport_id", id);
            result = (Sport)query.uniqueResult();
        }
        return result;
    }

    public List<Sport> getAllSports() {
        List<Sport> result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            result = session.createQuery("from Sport", Sport.class).list();
        }
        return result;
    }
}

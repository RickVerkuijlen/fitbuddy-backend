package context;

import objects.SportDTO;
import objects.SubscribedSportDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.List;

@Component
public class SportContext {

    public SportDTO getSportById(int id) {
        SportDTO result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from SportDTO s where s.id = :sport_id", SportDTO.class);
            query.setParameter("sport_id", id);
            result = (SportDTO)query.uniqueResult();
        }
        return result;
    }

    public List<SportDTO> getAllSports() {
        List<SportDTO> result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            result = session.createQuery("from SportDTO", SportDTO.class).list();
        }
        return result;
    }

    public boolean subscribeUserToSport(SubscribedSportDTO dto) {
        Transaction transaction = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(dto);

            transaction.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }
}

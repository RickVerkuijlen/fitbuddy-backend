package context;

import context.interfaces.IUserContext;
import objects.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.List;

@Component
public class UserContext implements IUserContext {
    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUserById(String id) {
        UserDTO result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from UserDTO u where u.uid = :userId", UserDTO.class);
            query.setParameter("userId", id);
            result = (UserDTO)query.uniqueResult();
        }
        return result;
    }

    @Override
    public boolean create(UserDTO entity) {
        Transaction transaction = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

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

    @Override
    public boolean update(UserDTO entity) {
        return false;
    }

    @Override
    public boolean delete(UserDTO entity) {
        return false;
    }
}

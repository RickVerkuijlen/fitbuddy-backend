package context;

import context.interfaces.CrudOperations;
import context.interfaces.IUserContext;
import domain.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

@Component
public class UserContext extends CrudOperations<User> implements IUserContext {

    @Override
    public User getUserById(String id) {
        User result = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User u where u.uid = :userId", User.class);
            query.setParameter("userId", id);
            result = (User)query.uniqueResult();
        }
        return result;
    }
}

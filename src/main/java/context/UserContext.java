package context;

import context.interfaces.CrudOperations;
import context.interfaces.IUserContext;
import objects.UserDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateInitialize;

import java.util.List;

@Component
public class UserContext extends CrudOperations<UserDTO> implements IUserContext {
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
}

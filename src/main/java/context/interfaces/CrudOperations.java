package context.interfaces;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HibernateInitialize;

public abstract class CrudOperations<T> implements IContext<T> {

    private static final Logger log = LoggerFactory.getLogger(CrudOperations.class);
    @Override
    public final boolean create(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

            return true;
        } catch (Exception e) {
            log.error(e.toString());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateInitialize.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            if(transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }
}

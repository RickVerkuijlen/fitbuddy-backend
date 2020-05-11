package context.interfaces;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateInitialize;

public abstract class CrudOperations<T> implements IContext<T> {
    @Override
    public final boolean create(T entity) {
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
            if(transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }
}

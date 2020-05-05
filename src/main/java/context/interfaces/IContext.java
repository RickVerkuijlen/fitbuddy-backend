package context.interfaces;

public interface IContext<T> {
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(T entity);
}

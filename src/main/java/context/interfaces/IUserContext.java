package context.interfaces;

import domain.User;

public interface IUserContext extends IContext<User> {
    User getUserById(String id);
}

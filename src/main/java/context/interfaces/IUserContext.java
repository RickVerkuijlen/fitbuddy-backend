package context.interfaces;

import domain.User;

import java.util.List;

public interface IUserContext extends IContext<User> {
    User getUserById(String id);
}

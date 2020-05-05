package context.interfaces;

import objects.UserDTO;

import java.util.List;

public interface IUserContext extends IContext<UserDTO> {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(String id);
}

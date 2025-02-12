package repository.User;

import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    public boolean updateUser(User user);
}

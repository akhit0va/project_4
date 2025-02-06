package service;

import models.User;
import repository.User.IUserRepository;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }
}

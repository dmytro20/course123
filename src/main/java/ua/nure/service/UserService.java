package ua.nure.service;

import ua.nure.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

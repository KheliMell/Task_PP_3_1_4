package ru.kata.spring.boot_security.demo.services.dao_services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(Long id, User user);

    void removeUser(Long id);

    User getUserById(Long id);

    User findUserByUsername(String username);

    List<User> getAllUsers();

    User findUserByEmail(String email);
}

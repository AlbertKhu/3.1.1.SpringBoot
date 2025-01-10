package ru.kata311.SpringBoot.service;

import ru.kata311.SpringBoot.model.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User findById(Long id);

}

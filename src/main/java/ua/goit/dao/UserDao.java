package ua.goit.dao;

import ua.goit.domain.User;

import java.util.Set;

public interface UserDao {
    Set<User> getAll();

    User findByUsername(String name);

    void add(User user);

    void edit(User user);
}
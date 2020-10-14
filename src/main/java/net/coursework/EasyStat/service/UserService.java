package net.coursework.EasyStat.service;

import net.coursework.EasyStat.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void Delete(Long id);
}

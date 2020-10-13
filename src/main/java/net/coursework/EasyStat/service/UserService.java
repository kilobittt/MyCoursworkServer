package net.coursework.EasyStat.service;

import net.coursework.EasyStat.model.User;

import java.util.List;

public interface UserService {

    User Register(User user);

    List<User> GetAll();

    User FindByUsername(String username);

    User FindById(Long id);

    void Delete(Long id);
}

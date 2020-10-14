package net.coursework.EasyStat.service.impl;

import liquibase.pro.packaged.A;
import lombok.extern.slf4j.Slf4j;
import net.coursework.EasyStat.model.Role;
import net.coursework.EasyStat.model.Status;
import net.coursework.EasyStat.model.User;
import net.coursework.EasyStat.repository.RoleRepository;
import net.coursework.EasyStat.repository.UserRepository;
import net.coursework.EasyStat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registredUser = userRepository.save(user);

        log.info("IN Register - user: {} successfully registered", registredUser);
        return registredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN GetAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN FindByUsername - {} user found by username {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        log.info("IN FindByUsername - {} user found by id {}", result, id);
        return result;
    }

    @Override
    public void Delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN Delete - user with id {} successfully deleted", id);
    }
}

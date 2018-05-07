package com.nub.userService.service.impl;

import com.nub.userService.entity.User;
import com.nub.userService.repository.UserRepository;
import com.nub.userService.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudService<User, String> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public User find(String id) { return userRepository.findById(id).get(); }

    @Override
    public Iterable<User> findAll() { return userRepository.findAll(); }

    @Override
    public boolean delete(String id) {
        userRepository.deleteById(id);
        return true;
    }
}

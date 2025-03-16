package com.example.simple_service.service;

import com.example.simple_service.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> getUserByEmail(String email); // Yeni metod
}
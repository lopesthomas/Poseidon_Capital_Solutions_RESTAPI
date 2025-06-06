package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * UserService provides methods to manage users in the application.
 * It interacts with the UserRepository to perform CRUD operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find all {@link User} entities from the repository
     * 
     * @return List of all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Sava or update a {@link User} entity to the repository
     * 
     * @param user the user to save or update
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Find a {@link User} entity by its ID
     * 
     * @param id the ID of the user to find
     * @return the User entity if found, otherwise null
     */
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Delete a {@link User} entity by its ID
     * 
     * @param id the ID of the user to delete
     */
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}

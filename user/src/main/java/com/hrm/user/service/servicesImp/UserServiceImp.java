package com.hrm.user.service.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrm.user.model.entity.User;
import com.hrm.user.repository.UserRepository;
import com.hrm.user.service.services.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        try {
            // Attempt to save the user
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Log the error and return null or a specific response
            logger.error("Error saving user: {}", e.getMessage(), e);
            throw new RuntimeException("User data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            // Log any other exception
            logger.error("Unexpected error occurred while saving user: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while saving user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> getUserById(String username) {

            return userRepository.findById(username);
       
    }
}

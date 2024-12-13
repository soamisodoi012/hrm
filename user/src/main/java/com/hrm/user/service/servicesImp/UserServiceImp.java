package com.hrm.user.service.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrm.user.model.dto.UserDto;
import com.hrm.user.model.entity.User;
import com.hrm.user.repository.UserRepository;
import com.hrm.user.service.services.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        try {
            // Attempt to save the user
            User leave = modelMapper.map(userDto, User.class);
            return userRepository.save(leave);
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

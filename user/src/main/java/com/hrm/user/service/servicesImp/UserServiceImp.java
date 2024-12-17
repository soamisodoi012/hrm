package com.hrm.user.service.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrm.user.model.dto.UserDto;
import com.hrm.user.model.entity.User;
import com.hrm.user.repository.UserRepository;
import com.hrm.user.service.services.UserService;

import jakarta.persistence.EntityNotFoundException;

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
            userDto.setLocked(false);
            User user = modelMapper.map(userDto, User.class);
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

  @Override
  public UserDto updateUser(String username, UserDto userDto) {
    Optional<User> optionalUser = userRepository.findById(username);

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        System.out.println("Before Update: " + user);

        // Update only non-null fields from userDto
        if (userDto.getAddress() != null) 
        user.setAddress(userDto.getAddress());
        if (userDto.getPhone() != null)
         user.setPhone(userDto.getPhone());
        if (userDto.getSalary() != null) 
        user.setSalary(userDto.getSalary());
        if (userDto.getTitle() != null) 
        user.setTitle(userDto.getTitle());
        if (userDto.getSex() != null) 
        user.setSex(userDto.getSex());
        if (userDto.getAge() != null)
         user.setAge(userDto.getAge());
        if (userDto.getTotalLeave() != null){
         user.setTotalLeave(userDto.getTotalLeave());}
         else{
            
         }
        if (userDto.getDepId() != null) 
        user.setDepId(userDto.getDepId());
        if (userDto.getRole() != null)
         user.setRole(userDto.getRole());
        if (userDto.getExperiance() != null)
         user.setExperiance(userDto.getExperiance());

        // Save the updated user entity
        userRepository.save(user);
        System.out.println("After Update: " + user);

        // Convert the updated entity back to DTO for the response
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setUsername(user.getUsername());
        updatedUserDto.setAddress(user.getAddress());
        updatedUserDto.setPhone(user.getPhone());
        updatedUserDto.setSalary(user.getSalary());
        updatedUserDto.setTitle(user.getTitle());
        updatedUserDto.setSex(user.getSex());
        updatedUserDto.setAge(user.getAge());
        updatedUserDto.setTotalLeave(user.getTotalLeave());
        updatedUserDto.setDepId(user.getDepId());
        updatedUserDto.setRole(user.getRole());
        updatedUserDto.setExperiance(user.getExperiance());

        return updatedUserDto;
    } else {
        throw new EntityNotFoundException("User not found with username: " + username);
    }
}


@Override
public List<User> getAllUser() {
return userRepository.findAll();
}

public UserDto lockUser(String username) {
    // Find the user entity by username
    User user = userRepository.findById(username)
            .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

    // Lock the user
    user.setLocked(true);

    // Save the updated user entity
    userRepository.save(user);

    // Convert the user entity back to DTO
    UserDto updatedUserDto = new UserDto();
    updatedUserDto.setUsername(user.getUsername());
    updatedUserDto.setLocked(user.isLocked());
    // Map additional fields if necessary

    return updatedUserDto;
}


}

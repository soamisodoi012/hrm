package com.hrm.user_org_dep.service.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrm.user_org_dep.model.dto.UserDto;
import com.hrm.user_org_dep.model.dto.UserResponse;
import com.hrm.user_org_dep.model.entity.Department;
import com.hrm.user_org_dep.model.entity.User;
import com.hrm.user_org_dep.repository.DepartmentRepository;
import com.hrm.user_org_dep.repository.UserRepository;
import com.hrm.user_org_dep.service.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentRepository departmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserDto userDto) {
        try {
            // Validate username as it is the primary key
            if (userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
                throw new RuntimeException("Username cannot be null or empty as it is the primary key.");
            }
    
            // Create a new User entity
            User user = new User();
            user.setUsername(userDto.getUsername()); // Set primary key
            user.setAddress(userDto.getAddress());
            user.setPhone(userDto.getPhone());
            user.setUserId(userDto.getUserId());
            user.setSalary(userDto.getSalary());
            user.setTitle(userDto.getTitle());
            user.setSex(userDto.getSex());
            user.setAge(userDto.getAge());
            user.setTotalLeave(userDto.getTotalLeave());
            user.setRole(userDto.getRole());
            user.setExperiance(userDto.getExperiance());
            user.setLocked(false);
    
            // Fetch and set the Department entity
            Optional<Department> department = departmentRepository.findById(userDto.getDepartment());
            if (department.isPresent()) {
                user.setDepartment(department.get());
            } else {
                throw new RuntimeException("Department not found with ID: " + userDto.getDepartment());
            }
    
            // Save the user entity
            userRepository.save(user);
    
            // Map User entity to UserResponse DTO
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());
            userResponse.setAddress(user.getAddress());
            userResponse.setPhone(user.getPhone());
            userResponse.setSalary(user.getSalary());
            userResponse.setAge(user.getAge());
            userResponse.setTotalLeave(user.getTotalLeave());
            userResponse.setRole(user.getRole());
            userResponse.setExperiance(user.getExperiance());
            userResponse.setUserId(user.getUserId());
            userResponse.setTitle(user.getTitle());
            userResponse.setSex(user.getSex());
            if (user.getDepartment() != null) {
                userResponse.setDepartment(user.getDepartment().getDepName());
            }
    
            return userResponse;
    
        } catch (DataIntegrityViolationException e) {
            logger.error("Data integrity error occurred: {}", e.getMessage(), e);
            throw new RuntimeException("A user with the same username already exists.");
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while creating the user.");
        }
    }

    @Override
    public UserResponse getUserById(String username) {

          Optional<User> user=  userRepository.findById(username);
          System.out.println("user"+user);
            UserResponse userResponse=new UserResponse();
            if (user.isPresent()) {
                userResponse.setAddress(user.get().getAddress());
                userResponse.setTotalLeave(user.get().getTotalLeave());
            }
            return userResponse;
    }

  @Override
public UserResponse updateUser(String username, UserDto userDto) {
    // Defensive check for null username
    if (username == null || username.trim().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be null or empty.");
    }
    System.out.println("Looking up user with username: " + username);

    Optional<User> optionalUser = userRepository.findById(username);

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        System.out.println("Before Update: " + user);

        // Update fields only if not null
        if (userDto.getAddress() != null) user.setAddress(userDto.getAddress());
        if (userDto.getPhone() != null) user.setPhone(userDto.getPhone());
        if (userDto.getSalary() != null) user.setSalary(userDto.getSalary());
        if (userDto.getTitle() != null) user.setTitle(userDto.getTitle());
        if (userDto.getSex() != null) user.setSex(userDto.getSex());
        if (userDto.getAge() != null) user.setAge(userDto.getAge());
        if (userDto.getTotalLeave() != null) user.setTotalLeave(userDto.getTotalLeave());

        // Update department if present
        if (userDto.getDepartment() != null) {
            Optional<Department> department = departmentRepository.findById(userDto.getDepartment());
            if (department.isPresent()) {
                user.setDepartment(department.get());
            } else {
                throw new RuntimeException("Department not found with ID: " + userDto.getDepartment());
            }
        }

        if (userDto.getRole() != null) user.setRole(userDto.getRole());
        if (userDto.getExperiance() != null) user.setExperiance(userDto.getExperiance());

        // Save the updated user entity
        userRepository.save(user);
        System.out.println("After Update: " + user);

        // Convert entity to DTO
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setAddress(user.getAddress());
        userResponse.setPhone(user.getPhone());
        userResponse.setSalary(user.getSalary());
        userResponse.setAge(user.getAge());
        userResponse.setTotalLeave(user.getTotalLeave());
        userResponse.setRole(user.getRole());
        userResponse.setExperiance(user.getExperiance());
        userResponse.setUserId(user.getUserId());
        userResponse.setTitle(user.getTitle());
        userResponse.setSex(user.getSex());
        if (user.getDepartment() != null) {
            userResponse.setDepartment(user.getDepartment().getDepName());
        }

        return userResponse;
    } else {
        throw new EntityNotFoundException("User not found with username: " + username);
    }
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
@Override
public List<UserResponse> getAllUser() {
    List<User> users = userRepository.findAll(); // Fetch all users
    return users.stream()
            .map(this::convertToUserResponse) // Convert each User to UserResponse
            .toList();
}

// Conversion method: User to UserResponse
private UserResponse convertToUserResponse(User user) {
    UserResponse response = new UserResponse();
    response.setUsername(user.getUsername());
    response.setAddress(user.getAddress());
    response.setPhone(user.getPhone());
    response.setUserId(user.getUserId());
    response.setSalary(user.getSalary() != null ? user.getSalary(): null);
    response.setTitle(user.getTitle());
    response.setSex(user.getSex());
    response.setAge(user.getAge() != null ? user.getAge(): null);
    response.setTotalLeave(user.getTotalLeave() != null ? user.getTotalLeave() : null);
    response.setRole(user.getRole());
    response.setExperiance(user.getExperiance());
    response.setLocked(user.isLocked());

    // Set department name if it exists
    if (user.getDepartment() != null) {
        response.setDepartment(user.getDepartment().getDepName());
    } else {
        response.setDepartment(null);
    }
    return response;
}

}

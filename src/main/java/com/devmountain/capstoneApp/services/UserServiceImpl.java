package com.devmountain.capstoneApp.services;

import com.devmountain.capstoneApp.dtos.UserDto;
import com.devmountain.capstoneApp.entities.User;
import com.devmountain.capstoneApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// what would the user need to do in terms of CRUD operations
// 2 different things, registering a new user and logging in an existing user
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional // This method handles registering a new user
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("User Has Been Added Successfully");
        return response;
    }

    // This method will handle logging an existing user into the app
    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add("User Login Successful");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Incorrect Username or Password");
            }
        } else {
            response.add("Incorrect Username or Password");
        }
        return response;
    }

}
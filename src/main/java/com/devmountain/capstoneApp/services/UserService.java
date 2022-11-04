package com.devmountain.capstoneApp.services;

import com.devmountain.capstoneApp.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
        // This method handles registering a new user
    List<String> addUser(UserDto userDto);

    // This method will handle logging an existing user into the app
    List<String> userLogin(UserDto userDto);
}

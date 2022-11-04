package com.devmountain.capstoneApp.controllers;

import com.devmountain.capstoneApp.dtos.UserDto;
import com.devmountain.capstoneApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

@PostMapping("/register") // this method will handle POST requests to register a new user
public List<String> addUser(@RequestBody UserDto userDto) {
    String passHash = passwordEncoder.encode(userDto.getPassword());
    userDto.setPassword(passHash);
    return userService.addUser(userDto);
}

@PostMapping("/login") // this method takes care of POST requests to log in an existing user to the app
public List<String> userLogin(@RequestBody UserDto userDto) { // this annotation makes the JSON request body become usable to us
    return userService.userLogin(userDto);
}
}

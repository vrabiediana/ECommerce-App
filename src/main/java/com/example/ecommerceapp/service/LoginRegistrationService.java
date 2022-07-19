package com.example.ecommerceapp.service;

import com.example.ecommerceapp.dto.UserDto;
import com.example.ecommerceapp.exception.DuplicateUsernameException;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(LoginRegistrationService.class);

    public void register(UserDto userDto) throws DuplicateUsernameException {
        logger.info("Attempting to register username " + userDto.getUsername());

        Optional<User> existingUser = userRepository.findUserByUsername(userDto.getUsername());
        if(existingUser.isPresent()) {
            logger.error("Username " + userDto.getUsername() + " already exists!");
            throw new DuplicateUsernameException();
        }

        User user = buildUser(userDto, passwordEncoder);

        logger.info("New user registered with name " + userDto.getUsername());
        userRepository.save(user);
    }

    private User buildUser(UserDto userDto, PasswordEncoder encoder) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userDto.getUserType().buildUser(userDto);
    }
}

package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.dto.LoginDto;
import com.example.ecommerceapp.dto.UserDto;
import com.example.ecommerceapp.exception.DuplicateUsernameException;
import com.example.ecommerceapp.security.jwt.JwtUtils;
import com.example.ecommerceapp.service.LoginRegistrationService;
import com.example.ecommerceapp.service.implementations.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private LoginRegistrationService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    void register(@Valid @RequestBody UserDto userDto) throws DuplicateUsernameException {
        logger.info("REQUEST : register - username " + userDto.getUsername());
        service.register(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        logger.info("Attempting to log in with username " + userDto.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsServiceImpl.UserDetailsImpl userDetails = (UserDetailsServiceImpl.UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new LoginDto(
                userDto.getUsername(),
                roles.get(0),
                jwt
        ));
    }

}

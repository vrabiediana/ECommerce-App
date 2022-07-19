package com.example.ecommerceapp.service;

import com.example.ecommerceapp.exception.AccessRestrictedToAdminException;
import com.example.ecommerceapp.exception.AccessRestrictedToCustomerException;
import com.example.ecommerceapp.exception.AuthenticationRequiredException;
import com.example.ecommerceapp.model.Admin;
import com.example.ecommerceapp.model.Customer;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.AdminRepository;
import com.example.ecommerceapp.repository.CustomerRepository;
import com.example.ecommerceapp.repository.UserRepository;
import com.example.ecommerceapp.service.implementations.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public User getCurrentUser() throws AuthenticationRequiredException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findUserByUsername(((UserDetailsServiceImpl.UserDetailsImpl) auth.getPrincipal()).getUsername());
        if (optionalUser.isEmpty()) {
            logger.warn("User attempt to access resource without authentication.");
            throw new AuthenticationRequiredException();
        }
        return optionalUser.get();
    }

    public Customer getCurrentCustomer() throws AccessRestrictedToCustomerException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsername(((UserDetailsServiceImpl.UserDetailsImpl) auth.getPrincipal()).getUsername());
        if (optionalCustomer.isEmpty()) {
            logger.warn("Non-customer attempted to access customer resources.");
            throw new AccessRestrictedToCustomerException();
        }
        return optionalCustomer.get();
    }

    public Admin getCurrentAdmin() throws AccessRestrictedToAdminException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Admin> optionalAdmin = adminRepository.findAdminByUsername(((UserDetailsServiceImpl.UserDetailsImpl) auth.getPrincipal()).getUsername());
        if (optionalAdmin.isEmpty()) {
            logger.warn("Non-admin attempted to access admin resources.");
            throw new AccessRestrictedToAdminException();
        }
        return optionalAdmin.get();
    }
}

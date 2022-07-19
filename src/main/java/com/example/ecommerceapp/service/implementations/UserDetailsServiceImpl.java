package com.example.ecommerceapp.service.implementations;

import com.example.ecommerceapp.model.Admin;
import com.example.ecommerceapp.model.Customer;
import com.example.ecommerceapp.model.User;
import com.example.ecommerceapp.repository.AdminRepository;
import com.example.ecommerceapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserDetailsServiceImpl(CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByUsername(username);
        if (optionalCustomer.isPresent()) {
            return new UserDetailsImpl(optionalCustomer.get());
        }
        Optional<Admin> optionalAdmin = adminRepository.findAdminByUsername(username);
        if(optionalAdmin.isPresent()) {
            return new UserDetailsImpl(optionalAdmin.get());
        }
        throw new UsernameNotFoundException("Username not found: " + username);
    }

    public static class UserDetailsImpl implements UserDetails {

        private final User user;
        private final Collection<GrantedAuthority> authorities;

        public enum Authorities {
            ADMIN,
            CUSTOMER
        }

        public UserDetailsImpl(Admin admin) {
            this.user = admin;
            this.authorities = List.of(new SimpleGrantedAuthority(Authorities.ADMIN.toString()));
        }

        public UserDetailsImpl(Customer customer) {
            this.user = customer;
            this.authorities = List.of(new SimpleGrantedAuthority(Authorities.CUSTOMER.toString()));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}

package com.example.ecommerceapp.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Customer extends User{
    public Customer(String name, String password) {
        this.setUsername(name);
        this.setPassword(password);
    }
}

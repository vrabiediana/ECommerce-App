package com.example.ecommerceapp.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Admin extends User{
    public Admin(String name, String password) {
        this.setUsername(name);
        this.setPassword(password);
    }
}

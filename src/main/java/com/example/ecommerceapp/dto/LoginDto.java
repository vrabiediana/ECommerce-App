package com.example.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String role;
    private String jwt;
}

package com.example.ecommerceapp.dto;

import com.example.ecommerceapp.model.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductDto {
    @NotNull
    private String code;


    private String name;
}

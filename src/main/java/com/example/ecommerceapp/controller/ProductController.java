package com.example.ecommerceapp.controller;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.repository.ProductRepository;
import com.example.ecommerceapp.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = {"", "/"})
    public @NotNull Iterable<Product> getProducts() {
        return service.getAllProducts();
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = service.addProduct(product);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/product/{id}")
                .buildAndExpand(product.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
    }
}

package com.example.ecommerceapp.service.implementations;

import com.example.ecommerceapp.model.Product;
import com.example.ecommerceapp.repository.ProductRepository;
import com.example.ecommerceapp.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    @Override
    public Product addProduct(Product product) {
        //TODO: validation? i guess?
        return repository.save(product);
    }
}

package com.example.shopdemo.service;

import com.example.shopdemo.entity.Product;
import com.example.shopdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product, ProductRepository> {

    protected ProductService(ProductRepository repository) {
        super(repository);
    }



}

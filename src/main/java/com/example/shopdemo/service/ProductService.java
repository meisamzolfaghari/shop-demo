package com.example.shopdemo.service;

import com.example.shopdemo.entity.Product;
import com.example.shopdemo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService extends AbstractService<Product, ProductRepository> {

    protected ProductService(ProductRepository repository) {
        super(repository);
    }

    public Optional<Product> findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public Optional<Product> findByIdWithFilledView(Long id) {
        return repository.findByIdWithFilledView(id);
    }
}

package com.example.shopdemo.service;

import com.example.shopdemo.entity.Category;
import com.example.shopdemo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService extends AbstractService<Category, CategoryRepository> {

    protected CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public Optional<Category> findByName(String name) {
        return repository.findByName(name);
    }

}

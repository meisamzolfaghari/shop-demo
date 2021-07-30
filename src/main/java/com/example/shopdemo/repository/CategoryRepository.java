package com.example.shopdemo.repository;

import com.example.shopdemo.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends AbstractEntityRepository<Category> {

    Optional<Category> findByName(String name);
}

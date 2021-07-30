package com.example.shopdemo.repository;

import com.example.shopdemo.entity.Product;

import java.util.Optional;

public interface ProductRepository extends AbstractEntityRepository<Product> {

    Optional<Object> findByName(String name);

}

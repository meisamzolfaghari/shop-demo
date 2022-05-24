package com.example.shopdemo.repository;

import com.example.shopdemo.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends AbstractEntityRepository<Product> {

    Optional<Product> findByName(String name);

    @Query("select p from Product p left join fetch p.reviews where p.id = :id")
    Optional<Product> findByIdWithFilledView(@Param("id") Long id);
}

package com.example.shopdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private double price;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductReview> reviews = new HashSet<>();

    @Formula("(select avg(pr.rate) from product_reviews pr where pr.product_id = id)")
    private Double averageRate;

    public void addToReviews(ProductReview productReview) {
        productReview.setProduct(this);
        reviews.add(productReview);
    }
}

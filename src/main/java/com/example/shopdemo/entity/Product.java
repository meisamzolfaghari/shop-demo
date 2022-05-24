package com.example.shopdemo.entity;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@SelectBeforeUpdate(false)
@DynamicUpdate(false)
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    private double price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductReview> reviews = new HashSet<>();

    public Product() {
        super();
    }

    public Product(String name, double price, Category category) {
        super();
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ProductReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ProductReview> reviews) {
        this.reviews = reviews;
    }

    public void addToReviews(ProductReview productReview) {
        productReview.setProduct(this);
        reviews.add(productReview);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

package com.example.shopdemo.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "product_reviews", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "user_id"})})
public class ProductReview extends AbstractEntity {

    @Column(nullable = false)
    private int rate;

    private String comment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(optional = false)
    private User user;

    public ProductReview() {
        super();
    }

    public ProductReview(int rate, String comment, Product product, User user) {
        super();
        this.rate = rate;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }

    public String getUserFullName() {
        return user.getFullName();
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductReview that = (ProductReview) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "rate=" + rate +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }
}

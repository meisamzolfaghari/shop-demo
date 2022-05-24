package com.example.shopdemo.controller.dto.product;

import com.example.shopdemo.controller.dto.category.CategoryFullDTO;
import com.example.shopdemo.controller.dto.product.review.ProductReviewFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFullDTO {

    private Long id;

    private String name;

    private double price;

    private CategoryFullDTO category;

    private Set<ProductReviewFullDTO> reviews;

}

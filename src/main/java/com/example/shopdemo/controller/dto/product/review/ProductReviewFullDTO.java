package com.example.shopdemo.controller.dto.product.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewFullDTO {

    private Long id;

    private Integer rate;

    private String comment;

    private String userFullName;

}

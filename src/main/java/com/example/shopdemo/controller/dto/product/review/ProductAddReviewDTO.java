package com.example.shopdemo.controller.dto.product.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddReviewDTO {

    @NotNull(message = "product id must be provided!")
    private Long productId;

    @Min(value = 1, message = "min rate value is one!")
    @Max(value = 5, message = "max rate value is five!")
    private int rate;

    private String comment;

}

package com.example.shopdemo.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAddDTO {

    @NotBlank(message = "product name must not be empty!")
    private String name;

    @NotNull(message = "price must be provided!")
    private Double price;

    @NotNull(message = "category id must be provided!")
    private Long categoryId;

}

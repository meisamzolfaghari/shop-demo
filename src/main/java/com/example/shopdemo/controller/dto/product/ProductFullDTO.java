package com.example.shopdemo.controller.dto.product;

import com.example.shopdemo.controller.dto.category.CategoryFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFullDTO {

    private Long id;

    private String name;

    private long price;

    private CategoryFullDTO category;

}

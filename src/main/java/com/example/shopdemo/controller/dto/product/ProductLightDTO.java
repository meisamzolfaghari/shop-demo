package com.example.shopdemo.controller.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLightDTO {

    private Long id;

    private String name;

    private long price;

}

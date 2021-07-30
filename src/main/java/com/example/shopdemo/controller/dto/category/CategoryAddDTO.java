package com.example.shopdemo.controller.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAddDTO {

    @NotBlank(message = "category name must not be empty!")
    private String name;

}

package com.example.shopdemo.controller;

import com.example.shopdemo.controller.dto.category.CategoryAddDTO;
import com.example.shopdemo.controller.dto.category.CategoryFullDTO;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryController {

    private ModelMapper modelMapper;

    private CategoryService categoryService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryFullDTO addCategory(@Valid CategoryAddDTO categoryAddDTO) {

        validateCategoryNameNotExist(categoryAddDTO);

        Category category = modelMapper.map(categoryAddDTO, Category.class);

        categoryService.save(category);

        return modelMapper.map(category, CategoryFullDTO.class);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("id") Long id) {

        Category category = getCategoryById(id);

        categoryService.delete(category);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CategoryFullDTO> getAllCategories(Pageable pageable) {
        return categoryService.findAll(pageable)
                .map(category -> modelMapper.map(category, CategoryFullDTO.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryFullDTO getCategory(@PathVariable("id") Long id) {

        Category category = getCategoryById(id);

        return modelMapper.map(category, CategoryFullDTO.class);
    }

    private void validateCategoryNameNotExist(CategoryAddDTO categoryAddDTO) {
        if (categoryService.findByName(categoryAddDTO.getName()).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "category with name: {}" + categoryAddDTO.getName() + " already exists!");
    }

    private Category getCategoryById(Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id: {}" + id + " was not found!"));
    }

}

package com.example.shopdemo.controller.api;

import com.example.shopdemo.controller.dto.category.CategoryAddDTO;
import com.example.shopdemo.controller.dto.category.CategoryFullDTO;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final ModelMapper modelMapper;

    private final CategoryService categoryService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryFullDTO addCategory(@Valid @RequestBody CategoryAddDTO categoryAddDTO) {

        validateCategoryNameNotExist(categoryAddDTO.getName());

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
    public Page<CategoryFullDTO> getAllCategories(@RequestParam(defaultValue = "0", required = false) int start,
                                                  @RequestParam(defaultValue = "10", required = false) int size) {
        return categoryService.findAll(PageRequest.of(start, size))
                .map(category -> modelMapper.map(category, CategoryFullDTO.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryFullDTO getCategory(@PathVariable("id") Long id) {

        Category category = getCategoryById(id);

        return modelMapper.map(category, CategoryFullDTO.class);
    }

    private void validateCategoryNameNotExist(String name) {
        if (categoryService.findByName(name).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format("category with name: %s already exists!", name));
    }

    private Category getCategoryById(Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("category with id: %s was not found!", id)));
    }

}

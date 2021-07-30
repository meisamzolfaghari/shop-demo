package com.example.shopdemo.controller.api;

import com.example.shopdemo.controller.dto.product.ProductAddDTO;
import com.example.shopdemo.controller.dto.product.ProductFullDTO;
import com.example.shopdemo.controller.dto.product.ProductLightDTO;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.entity.Product;
import com.example.shopdemo.service.CategoryService;
import com.example.shopdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ModelMapper modelMapper;

    private final ProductService productService;

    private final CategoryService categoryService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductFullDTO addProduct(@Validated @RequestBody ProductAddDTO productAddDTO) {

        validateProductNameNotExist(productAddDTO.getName());

        Category category = getCategoryById(productAddDTO.getCategoryId());

        Product product = modelMapper.map(productAddDTO, Product.class);

        product.setCategory(category);

        productService.save(product);

        return modelMapper.map(product, ProductFullDTO.class);
    }

    // TODO: 7/30/21 implement edit

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id) {

        Product product = getValidatedProductById(id);

        productService.delete(product);
    }
    @GetMapping
    public Page<ProductLightDTO> getAllProducts(@RequestParam(defaultValue = "0", required = false) int start,
                                                @RequestParam(defaultValue = "10", required = false) int size) {
        return productService.findAll(PageRequest.of(start, size))
                .map(product -> modelMapper.map(product, ProductLightDTO.class));
    }

    @GetMapping("/{id}")
    public ProductFullDTO getProduct(@PathVariable("id") Long id) {

        Product product = getValidatedProductById(id);

        return modelMapper.map(product, ProductFullDTO.class);
    }

    private void validateProductNameNotExist(String name) {
        if (productService.findByName(name).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format("product with name: %s already exists!", name));
    }

    private Category getCategoryById(Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("category with id: %s was not found!", id)));
    }

    private Product getValidatedProductById(Long id) {
        return productService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("product with id: %s was not found!", id)));
    }

}

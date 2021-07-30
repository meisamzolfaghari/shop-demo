package com.example.shopdemo.controller;

import com.example.shopdemo.controller.dto.product.ProductAddDTO;
import com.example.shopdemo.controller.dto.product.ProductFullDTO;
import com.example.shopdemo.controller.dto.product.ProductLightDTO;
import com.example.shopdemo.entity.Category;
import com.example.shopdemo.entity.Product;
import com.example.shopdemo.service.CategoryService;
import com.example.shopdemo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private ModelMapper modelMapper;

    private ProductService productService;

    private CategoryService categoryService;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductFullDTO addProduct(ProductAddDTO productAddDTO) {

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

        Product product = getProductById(id);

        productService.delete(product);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ProductLightDTO> getAllProducts(Pageable pageable) {
        return productService.findAll(pageable)
                .map(product -> modelMapper.map(product, ProductLightDTO.class));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductFullDTO getProduct(@PathVariable("id") Long id) {

        Product product = getProductById(id);

        return modelMapper.map(product, ProductFullDTO.class);
    }

    private void validateProductNameNotExist(String name) {
        if (productService.findByName(name).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "product with name: {}" + name + " already exists!");
    }

    private Category getCategoryById(Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id: {}" + id + " was not found!"));
    }

    private Product getProductById(Long id) {
        return productService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "product with id: {}" + id + " was not found!"));
    }

}

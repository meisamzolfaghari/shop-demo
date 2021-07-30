package com.example.shopdemo.controller.api;

import com.example.shopdemo.config.CurrentUser;
import com.example.shopdemo.controller.dto.product.ProductFullDTO;
import com.example.shopdemo.controller.dto.product.review.ProductAddReviewDTO;
import com.example.shopdemo.entity.Product;
import com.example.shopdemo.entity.ProductReview;
import com.example.shopdemo.entity.User;
import com.example.shopdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product/review")
public class ProductReviewController {

    private final ModelMapper modelMapper;

    private final ProductService productService;

    @PutMapping
    public ProductFullDTO addReview(@Validated @RequestBody ProductAddReviewDTO productAddReviewDTO,
                                     final Authentication authentication) {

        User user = getExistingUser(authentication);

        Product product = getValidatedProductById(productAddReviewDTO.getProductId());

        validateProductReviewNotExistByUser(product, user);

        ProductReview productReview = modelMapper.map(productAddReviewDTO, ProductReview.class);
        productReview.setUser(user);

        product.getReviews().add(productReview);

        productService.save(product);

        return modelMapper.map(product, ProductFullDTO.class);
    }

    // TODO: 7/30/21 add edit review

    private void validateProductReviewNotExistByUser(Product product, User user) {

        boolean userHaveReviewOnThisProduct = product.getReviews().stream()
                .anyMatch(productReview -> productReview.getUser().equals(user));

        if (userHaveReviewOnThisProduct)
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format("user: %s already has review for product with id: %s!",user.getEmail(), product.getId()));
    }

    private Product getValidatedProductById(Long id) {
        return productService.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("product with id: %s was not found!", id)));
    }

    private User getExistingUser(Authentication authentication) {
        return ((CurrentUser) authentication.getPrincipal()).getUser();
    }

}

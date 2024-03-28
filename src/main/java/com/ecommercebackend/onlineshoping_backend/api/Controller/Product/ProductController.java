package com.ecommercebackend.onlineshoping_backend.api.Controller.Product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.Product;
import com.ecommercebackend.onlineshoping_backend.api.Sevices.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts(LocalUser user){
        return productService.getProduct(user);
    }
    
}

package com.ecommercebackend.onlineshoping_backend.api.Sevices;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.Product;
import com.ecommercebackend.onlineshoping_backend.dao.ProductDAO;

@Service
public class ProductService {

    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getProduct ( LocalUser localUser){
        return productDAO.findAll(); 
    }

    
}

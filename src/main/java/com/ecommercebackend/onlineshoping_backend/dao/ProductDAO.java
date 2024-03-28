package com.ecommercebackend.onlineshoping_backend.dao;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommercebackend.onlineshoping_backend.Models.Product;

public interface ProductDAO extends ListCrudRepository<Product,Long> {
    
    
}

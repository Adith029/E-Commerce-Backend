package com.ecommercebackend.onlineshoping_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public long id;
    @JsonIgnore
    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "product_Id",nullable = false,unique = true)
    public Product product;
    public Inventory() {
    }

    public Inventory(Product product) {
        this.product = product;
    }
    @Column(name = "quantity",nullable = false)
    public int quantity;
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    
}

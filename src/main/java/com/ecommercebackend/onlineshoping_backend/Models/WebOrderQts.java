package com.ecommercebackend.onlineshoping_backend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="webOrderQts")
public class WebOrderQts {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable=false)
    public long id;
    @ManyToOne(optional = false)
    @JoinColumn(name ="product_id",nullable=false)
    private Product product;
    @Column(name = "quantity" ,nullable=false)
    public int quantity;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id" ,nullable=false)
    private WebOrder order;
    public WebOrderQts() {
    }

    public WebOrderQts(WebOrder order) {
        this.order = order;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}

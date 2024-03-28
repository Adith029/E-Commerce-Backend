package com.ecommercebackend.onlineshoping_backend.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public long id;
    @Column(name = "name",nullable = false)
    public String name;
    @Column(name ="shortDescription" ,nullable = false )
    public String shortDescription;
    @Column(name ="longDescription" ,nullable = false )
    public String longDescription;
    @Column(name ="price" ,nullable = false )
    public int price;
    @OneToOne(mappedBy = "product",cascade = CascadeType.REMOVE,optional = false, orphanRemoval = true)
    private Inventory inventory;
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getLongDescription() {
        return longDescription;
    }
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
}

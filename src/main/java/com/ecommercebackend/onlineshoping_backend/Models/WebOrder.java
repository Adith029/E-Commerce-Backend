package com.ecommercebackend.onlineshoping_backend.Models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "webOrder")
public class WebOrder {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id",nullable = false)
public long id;
@ManyToOne(optional = false)
@JoinColumn(name = "user_id", nullable = false)
public LocalUser user;    
@ManyToOne(optional = false)
@JoinColumn(name = "address_id",nullable =  false)
public UserAddress address;
@OneToMany(mappedBy =  "order",cascade = CascadeType.REMOVE,orphanRemoval = true)
public List<WebOrderQts> quantity= new ArrayList<>();


public WebOrder() {
}

public WebOrder(ArrayList<WebOrderQts> quantity) {
    this.quantity = quantity;
}
public List<WebOrderQts> getQuantity() {
    return quantity;
}

public long getId() {
    return id;
}
public void setId(long id) {
    this.id = id;
}
public LocalUser getUser() {
    return user;
}
public void setUser(LocalUser user) {
    this.user = user;
}
public UserAddress getAddress() {
    return address;
}
public void setAddress(UserAddress address) {
    this.address = address;
}
}

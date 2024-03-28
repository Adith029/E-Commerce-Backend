package com.ecommercebackend.onlineshoping_backend.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private LocalUser user;
    public UserAddress() {
    }

    public UserAddress(LocalUser user) {
        this.user = user;
    }
    @Column(name = "addressLine1",nullable = false)
    public String addressLine1;
    @Column(name = "addressLine2",nullable = false)
    public String addressLine2;
    @Column(name = "city",nullable = false)
    public String city;
    @Column(name = "country",nullable = false)
    public String country;
    @Column(name = "postcode",nullable = false)
    public int postCode;
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
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public int getPostCode() {
        return postCode;
    }
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }


}

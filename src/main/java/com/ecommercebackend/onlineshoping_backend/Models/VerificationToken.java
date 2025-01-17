package com.ecommercebackend.onlineshoping_backend.Models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_token" )
public class VerificationToken {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private Long id;
    @Lob
    @Column(name = "token",nullable = false,unique = true)
    private String token;
    @Column(name = "created_timestamp",nullable = false)
    private Timestamp createTimestamp;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private LocalUser user;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }
    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    public LocalUser getUser() {
        return user;
    }
    public void setUser(LocalUser user) {
        this.user = user;
    }
    
}

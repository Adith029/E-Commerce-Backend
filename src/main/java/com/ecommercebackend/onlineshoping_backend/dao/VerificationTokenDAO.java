package com.ecommercebackend.onlineshoping_backend.dao;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.VerificationToken;

public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);
    void deleteByUser(LocalUser user);
    
}

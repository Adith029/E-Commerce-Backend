package com.ecommercebackend.onlineshoping_backend.dao;

import java.util.Optional;


import org.springframework.data.repository.ListCrudRepository;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;





public interface LocalUserDao extends ListCrudRepository<LocalUser,Long>{

    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String email);
}

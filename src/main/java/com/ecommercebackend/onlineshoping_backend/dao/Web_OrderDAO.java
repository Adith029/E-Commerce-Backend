package com.ecommercebackend.onlineshoping_backend.dao;


import org.springframework.data.repository.ListCrudRepository;
import java.util.List;
import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.WebOrder;



public interface Web_OrderDAO extends ListCrudRepository<WebOrder, Long>{
    List<WebOrder> findByUser(LocalUser user);
}

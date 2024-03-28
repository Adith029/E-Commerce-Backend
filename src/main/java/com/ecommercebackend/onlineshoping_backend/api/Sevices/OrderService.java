package com.ecommercebackend.onlineshoping_backend.api.Sevices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.WebOrder;
import com.ecommercebackend.onlineshoping_backend.dao.Web_OrderDAO;

@Service
public class OrderService {

   private Web_OrderDAO web_OrderDAO;

public OrderService(Web_OrderDAO web_OrderDAO) {
    this.web_OrderDAO = web_OrderDAO;
}

public  List <WebOrder> getOrder(LocalUser user){
    return web_OrderDAO.findByUser(user);
}
    
}

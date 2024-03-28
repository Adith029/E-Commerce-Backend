package com.ecommercebackend.onlineshoping_backend.api.Controller.Order;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.Models.WebOrder;
import com.ecommercebackend.onlineshoping_backend.api.Sevices.OrderService;

@RestController
@RequestMapping("/Order")
public class OrderController {
    


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<WebOrder> getOrder(@AuthenticationPrincipal LocalUser user){
        return orderService.getOrder(user);
    }
}

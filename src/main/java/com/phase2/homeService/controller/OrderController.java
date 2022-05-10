package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.service.implementations.OrderServiceImple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImple orderService;

    public OrderController(OrderServiceImple orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody Order order){
        return ResponseEntity.ok(orderService.save(order));
    }

}

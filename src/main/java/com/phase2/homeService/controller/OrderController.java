package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import com.phase2.homeService.service.implementations.OrderServiceImple;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderServiceImple orderService;

    public OrderController(OrderServiceImple orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        order.setOrderStatus(OrderStatus.WAITING_FOR_PROFESSIONAL_OFFER);
        return ResponseEntity.ok(orderService.save(order));
    }

    @GetMapping("/getByCityAndService")
    public ResponseEntity<List<Order>> getByCityAndService(@RequestHeader String city, String serviceName) {
        return ResponseEntity.ok(orderService.getByCityAndServiceAndStatus(city, serviceName));
    }

    @GetMapping("/findById")
    public ResponseEntity<Order> findById(@RequestParam Integer id) {
        Order order = orderService.findById(id);
        if (order != null)
            return ResponseEntity.ok(order);
        else
            return ResponseEntity.notFound().build();
    }

}

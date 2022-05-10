package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.repository.OrderRepository;
import com.phase2.homeService.service.interfaces.OrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImple implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImple(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}

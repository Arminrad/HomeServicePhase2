package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> getByCityAndServiceAndStatus(String city, String serviceName);

    Order findById(Integer id);

}

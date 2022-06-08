package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Order save(Order order);

    List<Order> getByCityAndServiceAndStatus(String city, Set<Services> services);

    Order findById(Integer id);

    Order getById(Integer id);

    List<Order> getOrdersByCustomer(Customer customer);

    List<Order> takenAndDoneOrders();

}

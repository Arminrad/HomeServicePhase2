package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import com.phase2.homeService.repository.OrderRepository;
import com.phase2.homeService.service.interfaces.OrderService;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Service
public class OrderServiceImple implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImple(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
/*        order.setService(service);
        order.setCustomer(customer);*/
        order.setOrderStatus(OrderStatus.WAITING_FOR_PROFESSIONAL_OFFER);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getByCityAndServiceAndStatus(String city, Set<Services> services) {
        return orderRepository.getByCityAndServiceAndStatus(city, services);
    }

    @Override
    public Order findById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public Order getById(Integer id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderRepository.getOrdersByCustomer(customer);
    }

    @Override
    public List<Order> takenAndDoneOrders() {
        return orderRepository.takenAndDoneOrders();
    }

    @Override
    public List<Order> ordersOfTimePeriodAndOrderStatusAndServiceName(Timestamp firstDate, Timestamp secondDate, OrderStatus orderStatus, String serviceName) {
        return orderRepository.BasedOnTimePeriodAndOrderStatusAndServiceName(firstDate, secondDate, orderStatus, serviceName);
    }


}

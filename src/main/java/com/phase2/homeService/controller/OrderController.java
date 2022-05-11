package com.phase2.homeService.controller;

import com.phase2.homeService.dto.OrderDto;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.service.implementations.OrderServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.dozer.DozerBeanMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImple orderService;
    private final ServicesServiceImple serviceService;

    public OrderController(OrderServiceImple orderService, ServicesServiceImple serviceService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
    }

    @PostMapping("/save")
    public ResponseEntity<Order> save(@RequestBody OrderDto orderDto) {
        Services service = serviceService.getById(orderDto.getService_id());
        DozerBeanMapper mapper = new DozerBeanMapper();
        Order order = mapper.map(orderDto, Order.class);
        order.setService(service);
        System.out.println(order);
        //orderDto.setOrderStatus(OrderStatus.WAITING_FOR_PROFESSIONAL_OFFER);
        //return ResponseEntity.ok(orderService.save(order));
        return null;
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

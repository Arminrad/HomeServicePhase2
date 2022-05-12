package com.phase2.homeService.controller;

import com.phase2.homeService.dto.OrderDto;
import com.phase2.homeService.dto.ProfessionalDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import com.phase2.homeService.service.implementations.OrderServiceImple;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import org.aspectj.weaver.ast.Or;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImple orderService;
    private final ServicesServiceImple serviceService;
    private final CustomerServiceImple customerService;
    private final ProfessionalServiceImple professionalService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public OrderController(OrderServiceImple orderService,
                           ServicesServiceImple serviceService,
                           CustomerServiceImple customerService,
                           ProfessionalServiceImple professionalService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.customerService = customerService;
        this.professionalService = professionalService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        Services service = serviceService.getById(orderDto.getService_id());
        Order order = mapper.map(orderDto, Order.class);
        order.setService(service);
        Customer customer = customerService.getById(orderDto.getCustomer_id());
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.WAITING_FOR_PROFESSIONAL_OFFER);
        Order savedOrder = orderService.save(order);
        OrderDto savedOrderDto = modelMapper.map(savedOrder, OrderDto.class);
        return ResponseEntity.ok(savedOrderDto);
    }

    //change get to post**
    @PostMapping("/getByCityAndService")
    public ResponseEntity<List<OrderDto>> getByCityAndService(@RequestBody ProfessionalDto professionalDto) {
        Professional professional = professionalService.getById(professionalDto.getId());
        List<Order> orderList = orderService.getByCityAndServiceAndStatus(professional.getCity(), professional.getServices());
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o: orderList) {
            OrderDto savedOrderDto = modelMapper.map(o, OrderDto.class);
            orderDtos.add(savedOrderDto);
        }

        return ResponseEntity.ok(orderDtos);
    }

}

package com.phase2.homeService.controller;

import com.phase2.homeService.dto.OrderBasedOnTimePeriodDto;
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
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderServiceImple orderService;
    private final ServicesServiceImple serviceService;
    private final CustomerServiceImple customerService;
    private final ProfessionalServiceImple professionalService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public OrderController(OrderServiceImple orderService, ServicesServiceImple serviceService, CustomerServiceImple customerService, ProfessionalServiceImple professionalService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
        this.customerService = customerService;
        this.professionalService = professionalService;
        this.mapper = new DozerBeanMapper();
        this.modelMapper = new ModelMapper();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/save")
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        Services service = serviceService.getById(orderDto.getService_id());
        Customer customer = customerService.getById(orderDto.getCustomer_id());
        Order order = mapper.map(orderDto, Order.class);
        order.setService(service);
        order.setCustomer(customer);
        Order savedOrder = orderService.save(order);
        OrderDto savedOrderDto = modelMapper.map(savedOrder, OrderDto.class);
        return ResponseEntity.ok(savedOrderDto);
    }

    @PreAuthorize("hasRole('PROFESSIONAL')")
    @PostMapping("/getByCityAndService")
    public ResponseEntity<List<OrderDto>> getByCityAndService(@RequestBody ProfessionalDto professionalDto) {
        Professional professional = professionalService.getById(professionalDto.getId());
        List<Order> orderList = orderService.getByCityAndServiceAndStatus(professional.getCity(), professional.getServices());
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orderList) {
            OrderDto savedOrderDto = modelMapper.map(o, OrderDto.class);
            orderDtos.add(savedOrderDto);
        }
        return ResponseEntity.ok(orderDtos);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getOrders/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByCustomer(@PathVariable Long id) {
        Customer customer = customerService.getById(Math.toIntExact(id));
        List<Order> orderList = orderService.getOrdersByCustomer(customer);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o : orderList) {
            OrderDto savedOrderDto = modelMapper.map(o, OrderDto.class);
            orderDtos.add(savedOrderDto);
        }
        return ResponseEntity.ok(orderDtos);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/selectOffer")
    public ResponseEntity<OrderDto> selectOffer(@RequestBody OrderDto orderDto) {
        Professional professional = professionalService.getById(orderDto.getProfessional_id());
        Order order = orderService.getById(orderDto.getId());
        order.setOrderStatus(OrderStatus.PROFESSIONAL_IS_COMING);
        order.setProfessional(professional);
        Order savedOrder = orderService.save(order);
        OrderDto savedOrderDto = modelMapper.map(savedOrder, OrderDto.class);
        return ResponseEntity.ok(savedOrderDto);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/takenAndDoneOrders")
    public ResponseEntity<List<OrderDto>> takenAndDoneOrders() {
        List<Order> orders = orderService.takenAndDoneOrders();
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order o : orders) {
            OrderDto orderDto = modelMapper.map(o, OrderDto.class);
            ordersDto.add(orderDto);
        }
        return ResponseEntity.ok(ordersDto);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/timePeriodOrders")
    public ResponseEntity<List<OrderDto>> timePeriodOrders(@RequestBody OrderBasedOnTimePeriodDto orderBasedOnTimePeriodDto) {
        List<Order> orders = orderService.ordersOfTimePeriodAndOrderStatusAndServiceName(
                orderBasedOnTimePeriodDto.getFirstDate(),orderBasedOnTimePeriodDto.getSecondDate(),
                orderBasedOnTimePeriodDto.getOrderStatus(),orderBasedOnTimePeriodDto.getServiceName());
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order o:orders) {
            OrderDto returnedOrderDto = modelMapper.map(o, OrderDto.class);
            orderDtoList.add(returnedOrderDto);
        }
        return ResponseEntity.ok(orderDtoList);
    }
}

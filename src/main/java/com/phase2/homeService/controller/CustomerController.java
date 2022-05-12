package com.phase2.homeService.controller;

import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImple customerService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerServiceImple customerService) {
        this.customerService = customerService;
        this.modelMapper = new ModelMapper();
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        CustomerDto savedCustomerDto = modelMapper.map(savedCustomer, CustomerDto.class);
        return ResponseEntity.ok(savedCustomerDto);
    }

}

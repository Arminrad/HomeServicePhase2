package com.phase2.homeService.controller;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerServiceImple customerService;

    public CustomerController(CustomerServiceImple customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }



}

package com.phase2.homeService.controller;

import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import org.dozer.DozerBeanMapper;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImple customerService;
    private final DozerBeanMapper mapper;

    public CustomerController(CustomerServiceImple customerService) {
        this.customerService = customerService;
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute @RequestBody CustomerDto customerDto, Model model) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        model.addAttribute("name", savedCustomer.getFirstName());
        return "customer";
    }
}

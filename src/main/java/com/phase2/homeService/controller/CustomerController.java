package com.phase2.homeService.controller;

import com.phase2.homeService.config.SecurityUtil;
import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImple customerService;
    private final DozerBeanMapper mapper;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerServiceImple customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute @RequestBody CustomerDto customerDto, Model model) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        model.addAttribute("name", savedCustomer.getFirstName());
        return "customer";
    }

    @PostMapping(value = "/gridSearch")
    public ResponseEntity<List<CustomerDto>> gridSearch(@RequestBody DynamicSearchDto dynamicSearch) {
        List<Customer> customerList = customerService.filterCustomer(dynamicSearch);
        List<CustomerDto> dtoList = new ArrayList<>();
        for (Customer s:customerList
        ) {
            dtoList.add(mapper.map(s,CustomerDto.class));
        }
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<CustomerDto> getOrderByCustomer(@PathVariable Long id) {
        Customer customer = customerService.getById(Math.toIntExact(id));
        System.out.println(customer.getBalance());
            CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok(customerDto);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUser().toString();
    }

}

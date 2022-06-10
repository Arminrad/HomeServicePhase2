package com.phase2.homeService.controller;

//import com.phase2.homeService.config.SecurityUtil;
import com.phase2.homeService.config.SecurityUtil;
import com.phase2.homeService.dto.AccountCreditPaymentDto;
import com.phase2.homeService.dto.CustomerDto;
import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.dto.OnlinePaymentDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.base.User;
import com.phase2.homeService.service.implementations.CustomerServiceImple;
import com.phase2.homeService.service.implementations.OfferServiceImple;
import com.phase2.homeService.service.implementations.OrderServiceImple;
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
    private final OrderServiceImple orderService;
    private final OfferServiceImple offerService;

    public CustomerController(CustomerServiceImple customerService, ModelMapper modelMapper, OrderServiceImple orderService, OfferServiceImple offerService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.offerService = offerService;
        this.mapper = new DozerBeanMapper();
    }

    @PostMapping(value = "/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute @RequestBody CustomerDto customerDto, Model model) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerService.save(customer);
        model.addAttribute("name", savedCustomer.getFirstName());
        return "customer";
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getBalance/{id}")
    public ResponseEntity<CustomerDto> getOrderByCustomer(@PathVariable Long id) {
        Customer customer = customerService.getById(Math.toIntExact(id));
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return ResponseEntity.ok(customerDto);
    }

    //@PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody AccountCreditPaymentDto paymentDto) {
        Order order = orderService.getById(paymentDto.getOrderId());
        Offer offer = offerService.getById(paymentDto.getOfferId());
        Customer customer = customerService.getById(paymentDto.getCustomerId());
        String response = customerService.creditPayment(customer, offer, order);
        return ResponseEntity.ok(response);
    }

    //@PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(value = "/onlinePayment")
    public ResponseEntity<String> onlinePayment(@RequestBody OnlinePaymentDto onlinePaymentDto) {
        String message = customerService.onlinePayment(onlinePaymentDto.getOrderId(),onlinePaymentDto.getOfferId(),
                onlinePaymentDto.getCardNumber(),onlinePaymentDto.getCvv2(),
                onlinePaymentDto.getExpirationDate(),onlinePaymentDto.getSecondPassword());
        return ResponseEntity.ok(message);
    }
}

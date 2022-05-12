package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findByEmail(String email);

    Customer getById(Integer id);
}

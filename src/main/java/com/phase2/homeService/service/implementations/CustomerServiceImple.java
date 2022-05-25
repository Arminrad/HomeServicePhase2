package com.phase2.homeService.service.implementations;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.repository.CustomerRepository;
import com.phase2.homeService.service.interfaces.CustomerService;
import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImple implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImple(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer getById(Integer id) {
        return customerRepository.getById(id);
    }
}

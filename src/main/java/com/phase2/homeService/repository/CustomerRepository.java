package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Customer;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    Customer findByEmail(String email);

        @NonNull
        List<Customer> findAll(Specification<Customer> specification);
    }

package com.phase2.homeService.service.implementations;

import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.enumeration.Role;
import com.phase2.homeService.repository.CustomerRepository;
import com.phase2.homeService.service.interfaces.CustomerService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImple implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DozerBeanMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImple(CustomerRepository customerRepository, DozerBeanMapper mapper, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(Role.ROLE_CUSTOMER);
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



    public List<Customer> filterCustomer(DynamicSearchDto dynamicSearch){
        Customer customer = mapper.map(dynamicSearch,Customer.class);
        return customerRepository.findAll(userSpecification(customer));
    }

    private Specification<Customer> userSpecification(Customer customer){
        return (userRoot, query, criteriaBuilder)
                -> {
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            criteriaQuery.select(userRoot);

            List<Predicate> predicates = new ArrayList<>();
            //if(customer.getRole() != null )
                //predicates.add(criteriaBuilder.equal(userRoot.get("role"), Role.ROLE_CUSTOMER));
            if(customer.getFirstName() != null && !customer.getFirstName().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("firstName"),customer.getFirstName()));
            if(customer.getLastName() != null && !customer.getLastName().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("lastName"),customer.getLastName()));
            if(customer.getEmail() != null && !customer.getEmail().isEmpty())
                predicates.add(criteriaBuilder.equal(userRoot.get("email"),customer.getEmail()));

            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

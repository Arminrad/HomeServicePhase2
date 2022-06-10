package com.phase2.homeService.service.implementations;

import com.phase2.homeService.dto.DynamicSearchDto;
import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import com.phase2.homeService.entities.enumeration.Role;
import com.phase2.homeService.repository.CustomerRepository;
import com.phase2.homeService.service.interfaces.CustomerService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImple implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DozerBeanMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ProfessionalServiceImple professionalService;
    private final OrderServiceImple orderService;
    private final OfferServiceImple offerService;

    public CustomerServiceImple(CustomerRepository customerRepository, DozerBeanMapper mapper, PasswordEncoder passwordEncoder, ProfessionalServiceImple professionalService, OrderServiceImple orderService, OfferServiceImple offerService) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.professionalService = professionalService;
        this.orderService = orderService;
        this.offerService = offerService;
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

    @Override
    public String creditPayment(Customer customer, Offer offer, Order order) {
        if (customer.getBalance() >= offer.getProposedOfferPrice()) {
            double newBalance = customer.getBalance() - offer.getProposedOfferPrice();
            customer.setBalance(newBalance);
            customerRepository.save(customer);
            Double professionalShare = offer.getProposedOfferPrice() * 0.7;
            Professional professional = order.getProfessional();
            professional.setBalance(professional.getBalance() + professionalShare);
            professionalService.saveProfessional(professional);
            order.setOrderStatus(OrderStatus.ORDER_IS_PAID);
            Order updatedOrder = orderService.save(order);
            return "Order ID : " + updatedOrder.getId() + " paid successfully";
        } else
            return  "Your balance is not enough";
    }

    @Override
    public String onlinePayment(Integer offerId, Integer orderId, String cardNumber, String cvv2, Timestamp expirationDate, String secondPassword) {
        Order order = orderService.getById(orderId);
        Offer offer = offerService.getById(offerId);
        Long priceOrder = offer.getProposedOfferPrice();
        Professional professional = professionalService.getById(order.getProfessional().getId());
        Double amount = professional.getBalance() + priceOrder;
        professional.setBalance(amount);
        professionalService.saveProfessional(professional);
        order.setOrderStatus(OrderStatus.ORDER_IS_PAID);
        orderService.save(order);
        return "The payment was successful for the expert";
    }
}

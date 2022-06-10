package com.phase2.homeService.service.interfaces;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Offer;
import com.phase2.homeService.entities.Order;

import java.sql.Timestamp;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findByEmail(String email);

    Customer getById(Integer id);

    String creditPayment(Customer customer, Offer offer, Order order);

    String onlinePayment(Integer offerId, Integer orderId, String cardNumber, String cvv2, Timestamp expirationDate, String secondPassword);
}

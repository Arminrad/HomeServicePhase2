package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("FROM Order AS o WHERE o.city = :city AND " +
            "o.service IN (:services) AND " +
            "o.orderStatus = 'WAITING_FOR_PROFESSIONAL_OFFER' OR " +
            "o.orderStatus = 'WAITING_FOR_PROFESSIONAL_SELECTION' ")
    List<Order> getByCityAndServiceAndStatus(@Param("city") String city,
                                            @Param("services") Set<Services> services);

    //@Query("FROM Order AS o WHERE o.customer = :customer ")
    List<Order> getOrdersByCustomer(Customer customer);

    @Query("select o from Order o where o.orderStatus = 'PROFESSIONAL_IS_COMING' " +
            "or o.orderStatus = 'ORDER_IS_DONE'")
    List<Order> takenAndDoneOrders();

    @Query("select o from Order o where (o.orderRegistrationDate between :firstDate and :secondDate) " +
            "and o.orderStatus = :orderStatus and o.service.serviceName = :serviceName")
    List<Order> BasedOnTimePeriodAndOrderStatusAndServiceName(@Param("firstDate") Timestamp firstDate,
                                                              @Param("secondDate") Timestamp secondDate,
                                                              @Param("orderStatus")OrderStatus orderStatus,
                                                              @Param("serviceName") String serviceName);
}
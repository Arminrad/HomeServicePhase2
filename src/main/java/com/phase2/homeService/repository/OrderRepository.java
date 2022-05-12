package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("FROM Order AS o WHERE o.city = :city AND " +
            "o.service IN (:services) AND " +
            "o.orderStatus = 'WAITING_FOR_PROFESSIONAL_OFFER' OR " +
            "o.orderStatus = 'WAITING_FOR_PROFESSIONAL_SELECTION' ")
    List<Order> getByCityAndServiceAndStatus(@Param("city") String city,
                                            @Param("services") Set<Services> services);


}
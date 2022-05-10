package com.phase2.homeService.repository;

import com.phase2.homeService.entities.Order;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    //List<Order> getByCityEqualsAndServiceEqualsAAndOrderStatusEquals(String city, Services serviceName, OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE o.city = :city AND o.service.serviceName = :serviceName AND o.orderStatus = 'WAITING_FOR_PROFESSIONAL_OFFER'")
    List<Order> getByCityAndServiceAndStatus(@Param("city") String city, @Param("serviceName") String serviceName);
}
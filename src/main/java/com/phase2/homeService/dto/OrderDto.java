package com.phase2.homeService.dto;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString

    public class OrderDto {

        private Integer id;
        private Long proposedOrderPrice;
        private String description;
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        private Date orderRegistrationDate;
        private Date preferredDueDate;

        @Enumerated(EnumType.STRING)
        private OrderStatus orderStatus;

        @Column(nullable = false)
        private String city;
        private String street;
        private String alley;
        private String buildingNo;
        private Integer customer_id;
        private Integer service_id;
        private Integer professional_id;

    }

package com.phase2.homeService.entities;

import com.phase2.homeService.entities.base.BaseEntity;
import com.phase2.homeService.entities.enumeration.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {
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
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Services service;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<Offer> offers;

    @ManyToOne
    private Professional professional;
}

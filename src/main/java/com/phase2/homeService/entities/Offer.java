package com.phase2.homeService.entities;

import java.sql.Timestamp;
import com.phase2.homeService.entities.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Offer extends BaseEntity<Integer> {
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerRegistrationDate;
    private Long proposedOfferPrice;
    private Date serviceDuration;
    private Date startTime;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Professional professional;

}

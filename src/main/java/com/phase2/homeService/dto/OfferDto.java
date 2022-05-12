package com.phase2.homeService.dto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class OfferDto {
    private Integer id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date offerRegistrationDate;
    private Long proposedOfferPrice;
    private Date serviceDuration;
    private Date startTime;
    private Integer order_id;
    private Integer professional_id;
}

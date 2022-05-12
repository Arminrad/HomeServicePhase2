package com.phase2.homeService.dto;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class OfferDto {
    private Integer id;
    private Date offerRegistrationDate;
    private Long proposedOfferPrice;
    private Date serviceDuration;
    private Date startTime;
    private String order_id;
    private String professional_id;
}

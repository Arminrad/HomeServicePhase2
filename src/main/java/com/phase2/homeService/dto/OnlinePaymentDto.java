package com.phase2.homeService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OnlinePaymentDto {

    private Integer orderId;
    private Integer offerId;
    private String cardNumber;
    private String cvv2;
    private Timestamp expirationDate;
    private String secondPassword;
}

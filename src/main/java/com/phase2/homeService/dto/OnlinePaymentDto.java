package com.phase2.homeService.dto;

import com.phase2.homeService.validation.cardNumber.CardNumber;
import com.phase2.homeService.validation.cvv2.Cvv2;
import com.phase2.homeService.validation.secondPassword.SecondPassword;
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
    @CardNumber
    private String cardNumber;
    @Cvv2
    private String cvv2;
    private Timestamp expirationDate;
    @SecondPassword
    private String secondPassword;
}

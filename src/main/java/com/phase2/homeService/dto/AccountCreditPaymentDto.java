package com.phase2.homeService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreditPaymentDto {

    private Integer customerId;
    private Integer orderId;
    private Integer offerId;
}

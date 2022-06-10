package com.phase2.homeService.dto;

import com.phase2.homeService.entities.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderBasedOnTimePeriodDto {
    private Timestamp firstDate;
    private Timestamp secondDate;
    private OrderStatus orderStatus;
    private String specialtyName;
}

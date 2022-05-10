package com.phase2.homeService.entities;

import com.phase2.homeService.entities.Customer;
import com.phase2.homeService.entities.Professional;
import com.phase2.homeService.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Comment extends BaseEntity<Integer> {
    private String text;
    private Integer rating;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Professional professional;
}

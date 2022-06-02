package com.phase2.homeService.entities;

import com.phase2.homeService.entities.base.User;
import com.phase2.homeService.entities.enumeration.Role;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@DiscriminatorValue("customer")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Order> orders;

    public Customer(String firstName, String lastName, String email, String password, Date signUpDate, Double balance, Role type) {
        super(firstName, lastName, email, password, signUpDate, balance, type);
    }
}
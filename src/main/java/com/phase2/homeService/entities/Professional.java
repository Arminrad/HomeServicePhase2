package com.phase2.homeService.entities;

import com.phase2.homeService.entities.base.User;
import com.phase2.homeService.entities.enumeration.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@DiscriminatorValue("Professional")
public class Professional extends User {

    private Integer rating;
    @Column(nullable = true)
    private String city;
    @Column(nullable = true)
    private byte[] image;
    private String nationalCode;
    @OneToMany(mappedBy = "professional")
    private Set<Order> orders;
    @JoinTable(name = "professional_service", joinColumns = {@JoinColumn(name = "professional_id")}, inverseJoinColumns = {@JoinColumn(name = "service_id")})
    @ManyToMany
    @ToString.Exclude
    private Set<Services> services = new HashSet<>();
    @OneToMany(mappedBy = "professional")
    @ToString.Exclude
    private Set<Comment> comments;
    @OneToMany(mappedBy = "professional")
    @ToString.Exclude
    private Set<Offer> offers;


    public Professional(String firstName, String lastName, String email, String password, Date signUpDate, Double balance, Role role, String city, byte[] image, String nationalCode, Set<Services> services) {
        super(firstName, lastName, email, password, signUpDate, balance, role);
        this.city = city;
        this.image = image;
        this.nationalCode = nationalCode;
        this.services = services;
    }
}

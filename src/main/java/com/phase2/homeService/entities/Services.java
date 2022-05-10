package com.phase2.homeService.entities;

import com.phase2.homeService.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Services extends BaseEntity<Integer> {

    private String serviceName;

    @OneToOne
    private Services parent;

    private Long lowestPrice;

    @ManyToMany(mappedBy = "services")
    @ToString.Exclude
    private Set<Professional> professionals = new HashSet<>();

    @OneToMany(mappedBy = "service" )
    @ToString.Exclude
    private Set<Order> orders;

    public Services(String serviceName, Long lowestPrice) {
        this.serviceName = serviceName;
        this.lowestPrice = lowestPrice;
    }
}

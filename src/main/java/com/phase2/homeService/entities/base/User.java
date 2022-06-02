package com.phase2.homeService.entities.base;

import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.entities.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public class User extends BaseEntity<Integer> {

    @Column(nullable = true)
    private String firstName;
    @Column(nullable = true)
    private String lastName;
    @Column(unique = true, nullable = true)
    private String email = "qwe@gmail.com";
    @Column(nullable = true)
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpDate;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.NEW;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val =this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null: val.value();
    }

    public User(String firstName, String lastName, String email, String password, Date signUpDate, Double balance, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.signUpDate = signUpDate;
        this.balance = balance;
        this.role = role;
    }
}

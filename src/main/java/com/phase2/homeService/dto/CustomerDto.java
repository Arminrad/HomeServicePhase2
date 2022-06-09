package com.phase2.homeService.dto;
import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.entities.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date signUpDate;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.NEW;
    @Enumerated(EnumType.STRING)
    private Role type = Role.ROLE_CUSTOMER;
}

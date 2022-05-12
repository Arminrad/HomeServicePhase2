package com.phase2.homeService.dto;
import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.entities.enumeration.UserType;
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

public class ProfessionalDto {
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
    private UserStatus status = UserStatus.WAITING_FOR_CONFIRMATION;
    @Enumerated(EnumType.STRING)
    private UserType type = UserType.Professional;
    @Column(nullable = false)
    private String city;
    @Column(nullable = true)
    private byte[] image;
    private String nationalCode;
}

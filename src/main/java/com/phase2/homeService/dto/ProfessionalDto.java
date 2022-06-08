package com.phase2.homeService.dto;
import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.entities.enumeration.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/*@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString*/

public class ProfessionalDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
/*    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)*/
    private Date signUpDate;
    private Double balance;
/*    @Enumerated(EnumType.STRING)*/
    private UserStatus status = UserStatus.WAITING_FOR_CONFIRMATION;
/*    @Enumerated(EnumType.STRING)*/
    private Role type = Role.PROFESSIONAL;
/*    @Column(nullable = false)*/
    private String city;
    private MultipartFile image;
    private String nationalCode;
    private Integer[] services_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Role getType() {
        return type;
    }

    public void setType(Role type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer[] getServices_id() {
        return services_id;
    }

    public void setServices_id(Integer[] services_id) {
        this.services_id = services_id;
    }
}

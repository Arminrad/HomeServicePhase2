package com.phase2.homeService.entities.base;

import com.phase2.homeService.entities.enumeration.UserStatus;
import com.phase2.homeService.entities.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public class User extends BaseEntity<Integer> implements UserDetails {

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
    private boolean isEnabled = false;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", signUpDate=" + signUpDate +
                ", balance=" + balance +
                ", status=" + status +
                ", role=" + role +
                ", isEnabled=" + isEnabled;
    }
}

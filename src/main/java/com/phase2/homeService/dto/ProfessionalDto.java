package com.phase2.homeService.dto;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class ProfessionalDto {
    private Integer id;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private byte[] image;
    private String nationalCode;
}

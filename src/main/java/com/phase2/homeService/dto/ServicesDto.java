package com.phase2.homeService.dto;
import lombok.*;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class ServicesDto {
    private Integer id;
    private String serviceName;
    @OneToOne
    private String parent;
    private Long lowestPrice;
}

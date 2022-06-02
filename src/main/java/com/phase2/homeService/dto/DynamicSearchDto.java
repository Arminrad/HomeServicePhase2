package com.phase2.homeService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicSearchDto {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String service;
}

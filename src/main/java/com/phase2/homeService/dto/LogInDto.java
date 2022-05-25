package com.phase2.homeService.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class LogInDto {

    private String email;
    private String password;
}

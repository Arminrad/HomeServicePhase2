package com.phase2.homeService.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class CommentDto {
    private Integer id;
    private String text;
    private Integer rating;
    private Integer customer_id;
    private Integer professional_id;
}

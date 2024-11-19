package com.pankiv.movieland.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int id;
    private String text;
    @Getter
    private UserDto user;


}

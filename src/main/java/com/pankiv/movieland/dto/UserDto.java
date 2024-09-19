package com.pankiv.movieland.dto;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String nickname;
}

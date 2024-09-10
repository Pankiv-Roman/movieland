package com.pankiv.movieland.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Integer id;

    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRelease;
    private Double rating;
    private Double price;
    private String picturePath;
}

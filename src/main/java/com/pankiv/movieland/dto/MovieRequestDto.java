package com.pankiv.movieland.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {
    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;
    private List<Integer> genres;
    private List<Integer> countries;
}
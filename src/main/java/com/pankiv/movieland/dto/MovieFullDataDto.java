package com.pankiv.movieland.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFullDataDto {
    private Integer id;

    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;
    private List<CountryDto> countries;
    private List<GenreDto> genres;
    private List<ReviewDto> reviews;
}

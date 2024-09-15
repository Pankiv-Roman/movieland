package com.pankiv.movieland.dto;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Movie;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Integer id;

    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;

    public MovieDto(Movie movie, List<Genre> genres) {
    }
}

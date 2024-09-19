package com.pankiv.movieland.dto;

import com.pankiv.movieland.entity.Movie;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

    private Integer id;
    private String genre;
    private List<Movie> movies;
}



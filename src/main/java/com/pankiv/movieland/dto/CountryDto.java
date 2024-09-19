package com.pankiv.movieland.dto;

import com.pankiv.movieland.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private int id;
    private String name;
    private List<Movie> movies;
}

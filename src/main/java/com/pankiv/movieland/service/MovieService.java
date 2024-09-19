package com.pankiv.movieland.service;


import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<MovieDto> getListMovies(String ratingSortOrder, String priceSortOrder);

    List<MovieDto> getTreeRandomMovies();

    List<MovieDto> getMoviesByGenreId(Long genreId);

    Movie getMovieByIdWithDetails(Integer movieId);
}

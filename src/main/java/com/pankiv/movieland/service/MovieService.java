package com.pankiv.movieland.service;


import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.dto.MovieRequestDto;
import com.pankiv.movieland.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<MovieDto> getListMovies(String ratingSortOrder, String priceSortOrder);

    List<MovieDto> getTreeRandomMovies();

    List<MovieDto> getMoviesByGenreId(Long genreId);

    Optional<MovieFullDataDto> getMovieByIdWithCurrency(Integer id, String currency);

    Movie addMovie(MovieRequestDto movieRequestDto);

    Movie editMovie(Integer id, MovieRequestDto movieRequestDto);
}

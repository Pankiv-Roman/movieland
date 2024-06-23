package com.pankiv.movieland.service;


import com.pankiv.movieland.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getListMovies(String rating, String price);

    List<Movie> getTreeRandomMovies();

    List<Movie> getMoviesByGenreId(Long genreId);
}

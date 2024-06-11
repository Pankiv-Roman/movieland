package com.pankiv.movieland.service;


import com.pankiv.movieland.entity.Movie;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MovieService {
    List<Movie> getListMovies(Double rating, String sort);

    List<Movie> getTreeRandomMovies();

    List<Movie> getMoviesByGenreId(Long genreId);
}

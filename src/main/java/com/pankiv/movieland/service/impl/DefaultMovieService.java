package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.service.MovieService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;

    public DefaultMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getListMovies(Double rating, String sort) {
        if (Objects.equals(sort, "desc")) {
            return movieRepository.findAllAndSortByRating(rating) ;
        }
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getTreeRandomMovies() {
        return movieRepository.findAllTreeRandom();
    }

        @Override
    public List<Movie> getMoviesByGenreId(Long genreId) {
        return movieRepository.findByGenreId(genreId);
    }
}

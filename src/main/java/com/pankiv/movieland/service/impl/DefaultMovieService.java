package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.service.MovieService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;

    public DefaultMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getListMovies(String rating, String price) {
        Sort sort = getSort(rating, price);
        return movieRepository.findAll(sort);
    }

    private Sort getSort(String rating, String price) {
        if (rating != null) {
            return Sort.by(Sort.Order.desc("rating"));
        } else if (price != null) {
            return switch (price) {
                case "asc" -> Sort.by(Sort.Order.asc("price"));
                case "desc" -> Sort.by(Sort.Order.desc("price"));
                default -> Sort.unsorted();
            };
        } else {
            return Sort.unsorted();
        }
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

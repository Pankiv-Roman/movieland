package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.mapper.MovieMapper;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.Hibernate.initialize;

@Service
@AllArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public List<MovieDto> getListMovies(String ratingSortOrder, String priceSortOrder) {
        if (ratingSortOrder != null) {
            return movieMapper.toDtoList(movieRepository.findAllBySortByRatingDesc());
        }
        if (priceSortOrder != null) {
            if (priceSortOrder.equalsIgnoreCase("desc")) {
                return movieMapper.toDtoList(movieRepository.findAllBySortByPriceDesc());
            } else if (priceSortOrder.equalsIgnoreCase("asc")) {
                return movieMapper.toDtoList(movieRepository.findAllBySortByPriceAsc());
            }
        }
        return movieMapper.toDtoList(movieRepository.findAllMovies());
    }

    @Override
    public List<MovieDto> getTreeRandomMovies() {
        return movieMapper.toDtoList(movieRepository.findAllTreeRandom());
    }

    @Override
    public List<MovieDto> getMoviesByGenreId(Long genreId) {
        return movieMapper.toDtoList(movieRepository.findByGenreId(genreId));
    }

    @Override
    public Movie getMovieByIdWithDetails(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()
                -> new EntityNotFoundException("Movie not found!"));
        initialize(movie.getGenres());
        initialize(movie.getCountries());
        initialize(movie.getReviews());
        return movie;
    }
}

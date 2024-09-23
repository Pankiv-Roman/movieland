package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.mapper.MovieFullDataMapper;
import com.pankiv.movieland.mapper.MovieMapper;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieFullDataMapper movieFullDataMapper;
    private final NbuCurrencyService nbuCurrencyService;

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
    public Optional<MovieFullDataDto> getMovieByIdWithCurrency(Integer id, String currency) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            MovieFullDataDto movieDto = movieFullDataMapper.toDto(movie.get());

            double rate = nbuCurrencyService.getRate(currency);
            movieDto.setPrice(movieDto.getPrice() / rate);
            movieDto.setPrice(Math.round(movieDto.getPrice() * 100) / 100.0);

            return Optional.of(movieDto);
        } else {
            return Optional.empty();
        }
    }
}

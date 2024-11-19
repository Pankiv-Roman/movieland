package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.dto.MovieRequestDto;
import com.pankiv.movieland.entity.Country;
import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.entity.Review;
import com.pankiv.movieland.mapper.MovieFullDataMapper;
import com.pankiv.movieland.mapper.MovieMapper;
import com.pankiv.movieland.repository.CountryRepository;
import com.pankiv.movieland.repository.GenreRepository;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.repository.ReviewRepository;
import com.pankiv.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DefaultMovieService implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieFullDataMapper movieFullDataMapper;
    private final NbuCurrencyService nbuCurrencyService;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;

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

    @Transactional
    public Movie addMovie(MovieRequestDto movieRequestDto) {
        Movie addMovie = new Movie();
        return getMovie(movieRequestDto, addMovie);
    }

    @Override
    public Movie editMovie(Integer id, MovieRequestDto movieRequestDto) {
        Movie editMovie = movieRepository.getReferenceById(id);
        return getMovie(movieRequestDto, editMovie);
    }

    @Override
    public List<Genre> getGenres(Long movieId) {
        return genreRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<Review> getReviews(Long movieId) {
        return reviewRepository.findAllByMovieId(movieId);
    }

    @Override
    public List<Country> getCountries(Long movieId) {
        return countryRepository.findAllByMovieId(movieId);
    }

    private @NotNull Movie getMovie(@NotNull MovieRequestDto movieRequestDto, @NotNull Movie addMovie) {
        addMovie.setNameUkrainian(movieRequestDto.getNameUkrainian());
        addMovie.setNameNative(movieRequestDto.getNameNative());
        addMovie.setYearOfRelease(movieRequestDto.getYearOfRelease());
        addMovie.setDescription(movieRequestDto.getDescription());
        addMovie.setRating(movieRequestDto.getRating());
        addMovie.setPrice(movieRequestDto.getPrice());
        addMovie.setPicturePath(movieRequestDto.getPicturePath());

        List<Genre> genres = genreRepository.findAllById(movieRequestDto.getGenres());
        List<Country> countries = countryRepository.findAllById(movieRequestDto.getCountries());

        addMovie.setGenres(genres);
        addMovie.setCountries(countries);

        return movieRepository.save(addMovie);
    }
}

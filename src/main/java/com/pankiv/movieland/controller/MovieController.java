package com.pankiv.movieland.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequestMapping("api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping()
    public List<MovieDto> getListMovies(@RequestParam(name = "rating", required = false) String ratingSortOrder,
                                        @RequestParam(name = "price", required = false) String priceSortOrder) {
        return movieService.getListMovies(ratingSortOrder, priceSortOrder);
    }

    @GetMapping("/random")
    public List<MovieDto> getTreeRandomMovies() {
        return movieService.getTreeRandomMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieByIdWithDetails(@PathVariable Integer movieId) {
        Movie movie = movieService.getMovieByIdWithDetails(movieId);
        Hibernate.initialize(movie.getGenres());
        Hibernate.initialize(movie.getCountries());
        Hibernate.initialize(movie.getReviews());
        movie.getReviews().forEach(review -> Hibernate.initialize(review.getUser()));
        return movie;
    }
}

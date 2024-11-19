package com.pankiv.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.dto.MovieRequestDto;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import com.pankiv.movieland.service.impl.MovieEnrichmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequestMapping("api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieEnrichmentService movieEnrichmentService;

    @GetMapping()
    public List<MovieDto> getListMovies(@RequestParam(name = "rating", required = false) String ratingSortOrder,
                                        @RequestParam(name = "price", required = false) String priceSortOrder) {
        return movieService.getListMovies(ratingSortOrder, priceSortOrder);
    }

    @GetMapping("/random")
    public List<MovieDto> getTreeRandomMovies() {
        return movieService.getTreeRandomMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieFullDataDto> getMovieById(@PathVariable Integer id,
                                                         @RequestParam(value = "currency", defaultValue = "UAH") String currency) {
        movieEnrichmentService.enrichMovieData((long) id);
        Optional<MovieFullDataDto> movie = movieService.getMovieByIdWithCurrency(id, currency.toUpperCase());
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieService.addMovie(movieRequestDto);
        return ResponseEntity.ok(movie);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> editMovie(@PathVariable Integer id, @RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = movieService.editMovie(id, movieRequestDto);
        return ResponseEntity.ok(movie);
    }
}

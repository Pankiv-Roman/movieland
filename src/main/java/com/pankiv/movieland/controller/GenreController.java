package com.pankiv.movieland.controller;


import com.pankiv.movieland.dto.GenreDto;
import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.service.GenreService;
import com.pankiv.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/genres/")
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{genreId}")
    public List<MovieDto> getMoviesByGenreId(@PathVariable Long genreId) {
        return movieService.getMoviesByGenreId(genreId);
    }
}

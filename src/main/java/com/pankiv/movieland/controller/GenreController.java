package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.GenreService;
import com.pankiv.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/genres/")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{genreId}")
    public List<Movie> getMoviesByGenreId(@PathVariable Long genreId) {
        return movieService.getMoviesByGenreId(genreId);
    }
}

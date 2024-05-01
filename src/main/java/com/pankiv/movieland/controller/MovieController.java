package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("movies")
    public List<Movie> getListMovies() {
        return movieService.getListMovies();
    }
}
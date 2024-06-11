package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping()
    public List<Movie> getListMovies(@RequestParam(required = false) Double rating,
                                     @RequestParam(required = false) String sort) {
        return movieService.getListMovies(rating, sort);
    }

    @GetMapping("/random")
    public List<Movie> getTreeRandomMovies() {
        return movieService.getTreeRandomMovies();
    }
}

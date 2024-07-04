package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping()
    public List<Movie> getListMovies(@RequestParam(name = "rating", required = false) String ratingSortOrder,
                                     @RequestParam(name = "price", required = false) String priceSortOrder) {
        return movieService.getListMovies(ratingSortOrder, priceSortOrder);
    }

    @GetMapping("/random")
    public List<Movie> getTreeRandomMovies() {
        return movieService.getTreeRandomMovies();
    }
}

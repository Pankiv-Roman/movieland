package com.pankiv.movieland.service;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class MovieServiceTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("Test get tree random movies")
    void testGetTreeRandomMovies() {
        Movie movie1 = new Movie();
        movieRepository.save(movie1);
        Movie movie2 = new Movie();
        movieRepository.save(movie2);
        Movie movie3 = new Movie();
        movieRepository.save(movie3);
        Movie movie4 = new Movie();
        movieRepository.save(movie4);

        List<Movie> movies = movieService.getTreeRandomMovies();

        assertNotNull(movies);
        assertEquals(3, movies.size());
    }

    @AfterEach
    @Transactional
    void tearDown() {
        movieRepository.deleteAll();
    }
}

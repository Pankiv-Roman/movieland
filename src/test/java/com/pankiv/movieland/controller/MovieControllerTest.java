package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

 class MovieControllerTest {
    List<Movie> movies;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private MovieService movieService;
    private Movie movie1;
    private Movie movie2;

    @Test
    @DisplayName("Test get movies")
    void testGetMovies() throws Exception {

        movie1 = createMovie(1L, "Форсаж", "Fast and Fusion", 2003, 7.7, 12.3, "/fast-and-fusion/images/fast-and-fusion.jpg");
        movie2 = createMovie(2L, "Форсаж2", "Fast and Fusion2", 2004, 7.9, 12.9, "/fast-and-fusion2/images/fast-and-fusion2.jpg");

        movies = Arrays.asList(movie1, movie2);
        when(movieService.getListMovies()).thenReturn(movies);
        ResultActions resultActions = mockMvc.perform(get("/api/v1/movies")
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].nameUkrainian").value("Форсаж"))
                .andExpect(jsonPath("$.[0].nameNative").value("Fast and Fusion"))
                .andExpect(jsonPath("$.[0].yearOfRealise").value(2003))
                .andExpect(jsonPath("$.[0].rating").value(7.7))
                .andExpect(jsonPath("$.[0].price").value(12.3))
                .andExpect(jsonPath("$.[0].picturePath").value("/fast-and-fusion/images/fast-and-fusion.jpg"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].nameUkrainian").value("Форсаж2"))
                .andExpect(jsonPath("$.[1].nameNative").value("Fast and Fusion2"))
                .andExpect(jsonPath("$.[1].yearOfRealise").value(2004))
                .andExpect(jsonPath("$.[1].rating").value(7.9))
                .andExpect(jsonPath("$.[1].price").value(12.9))
                .andExpect(jsonPath("$.[1].picturePath").value("/fast-and-fusion2/images/fast-and-fusion2.jpg"));

    }

    private Movie createMovie(Long id, String nameUkrainian, String nameNative, Integer yearOfRealise,
                              Double rating, Double price, String picturePath) {
        return Movie.movieBuilder()
                .id(id)
                .nameUkrainian(nameUkrainian)
                .nameNative(nameNative)
                .yearOfRealise(yearOfRealise)
                .rating(rating)
                .price(price)
                .picturePath(picturePath)
                .build();
    }
}
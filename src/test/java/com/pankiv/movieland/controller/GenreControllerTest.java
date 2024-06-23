package com.pankiv.movieland.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.pankiv.movieland.AbstractBaseITest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class GenreControllerTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get all genres")
    void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/api/v1/genres/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].genre").value("Драма"))
                .andExpect(jsonPath("$.length()").value(15))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Get movies by genre")
    void testGetMoviesByGenre() throws Exception {
        mockMvc.perform(get("/api/v1/genres/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id").value(4))
                .andExpect(status().isOk());
    }
}
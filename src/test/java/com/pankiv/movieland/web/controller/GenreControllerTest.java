package com.pankiv.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.pankiv.movieland.AbstractBaseITest;
import com.pankiv.movieland.service.impl.MovieEnrichmentService;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class GenreControllerTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieEnrichmentService movieEnrichmentService;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_scheme_history")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get all genres")
    void testGetAllGenres() throws Exception {
        SQLStatementCountValidator.reset();
        mockMvc.perform(get("/api/v1/genres/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].genre").value("Драма"))
                .andExpect(jsonPath("$.length()").value(15))
                .andExpect(status().isOk());
        assertSelectCount(1);
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml",
    cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_scheme_history")
    @DisplayName("Get movies by genre")
    void testGetMoviesByGenre() throws Exception {
        SQLStatementCountValidator.reset();
        mockMvc.perform(get("/api/v1/genres/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id").value(4))
                .andExpect(status().isOk());
        assertSelectCount(1);
    }
}
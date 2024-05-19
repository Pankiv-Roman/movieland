package com.pankiv.movieland.controller;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.service.GenreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest {
    List<Genre> genres;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    private final Genre genre1 = createGenre(1L, "Action");
    private final Genre genre2 = createGenre(2L, "Cartoon");

    @Test
    @DisplayName("Get all genres")
    void testGetAllGenres() throws Exception {
        genres = Arrays.asList(genre1, genre2);
        when(genreService.getAllGenres()).thenReturn(genres);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/genres/")
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].genre").value("Action"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].genre").value("Cartoon"));
    }
    private Genre createGenre(Long id, String genre) {
        return Genre.genreBuilder()
                .id(id)
                .genre(genre)
                .build();
    }
}

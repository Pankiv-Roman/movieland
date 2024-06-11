package com.pankiv.movieland.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.pankiv.movieland.AbstractBaseITest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DBRider
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
class GenreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    void testGetAllGenres() throws Exception {
        mockMvc.perform(get("/api/v1/genres/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }




}
//    List<Genre> genres;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GenreService genreService;
//
//    private final Genre genre1 = createGenre(1L, "Action");
//    private final Genre genre2 = createGenre(2L, "Cartoon");
//
//    @Test
//    @DisplayName("Get all genres")
//    void testGetAllGenres() throws Exception {
//        genres = Arrays.asList(genre1, genre2);
//        when(genreService.getAllGenres()).thenReturn(genres);
//
//        ResultActions resultActions = mockMvc.perform(get("/api/v1/genres/")
//                .accept(MediaType.APPLICATION_JSON));
//
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].genre").value("Action"))
//                .andExpect(jsonPath("$[1].id").value(2))
//                .andExpect(jsonPath("$[1].genre").value("Cartoon"));
//    }
//    private Genre createGenre(Long id, String genre) {
//        return Genre.genreBuilder()
//                .id(id)
//                .genre(genre)
//                .build();
//    }
//}

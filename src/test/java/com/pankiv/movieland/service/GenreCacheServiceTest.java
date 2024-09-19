package com.pankiv.movieland.service;

import com.github.database.rider.core.api.dataset.DataSet;
import com.pankiv.movieland.AbstractBaseITest;
import com.pankiv.movieland.entity.Genre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class GenreCacheServiceTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GenreCacheService cacheService;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get genre from cache")
    void testGetAllGenreCache() {
        List<Genre> getAllGenres = cacheService.getAllGenresFromCache();
        assertNotNull(getAllGenres);
        assertEquals(15, getAllGenres.size());
    }


    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    void testChangeDataOnCache() {
        List<Genre> getAllGenres = cacheService.getAllGenresFromCache();

        getAllGenres.get(0).setGenre("test");

        assertNotNull(getAllGenres);
        assertNotEquals("test", getAllGenres.get(0).getGenre());

        List<Genre> getAllGenresAfterChange = cacheService.getAllGenresFromCache();

        assertNotEquals("test", getAllGenresAfterChange.get(0).getGenre());
    }
}
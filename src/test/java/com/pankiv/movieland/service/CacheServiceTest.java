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
class CacheServiceTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CacheService cacheService;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get genre from cache")
    void testGetAllGenreCache() {
        List<Genre> getAllGenres = cacheService.getAllGenresFromCache();
        assertNotNull(getAllGenres);
        assertEquals(15, getAllGenres.size());
    }
}
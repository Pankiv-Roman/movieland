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
class MovieControllerTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get movies")
    void testGetMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nameNative").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].nameUkrainian").value("Втеча з Шовшенка"))
                .andExpect(jsonPath("$[0].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[0].price").value(123.45))
                .andExpect(jsonPath("$[0].rating").value(8.9))
                .andExpect(jsonPath("$[0].yearOfRealise").value(1994))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(status().isOk());
    }
    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get movies with sort by rating desc")
    void testGetMoviesWithSortByRatingDesc() throws Exception {
        mockMvc.perform(get("/api/v1/movies?rating=desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nameNative").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].nameUkrainian").value("Втеча з Шовшенка"))
                .andExpect(jsonPath("$[0].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[0].price").value(123.45))
                .andExpect(jsonPath("$[0].rating").value(8.9))
                .andExpect(jsonPath("$[0].yearOfRealise").value(1994))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nameNative").value("The Green Mile"))
                .andExpect(jsonPath("$[1].nameUkrainian").value("Зелена миля"))
                .andExpect(jsonPath("$[1].picturePath").value("https://uk.wikipedia.org/wiki/%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F_%28%D1%84%D1%96%D0%BB%D1%8C%D0%BC%29#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Green_mile_(%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F).jpg"))
                .andExpect(jsonPath("$[1].price").value(134.67))
                .andExpect(jsonPath("$[1].rating").value(8.9))
                .andExpect(jsonPath("$[1].yearOfRealise").value(1999))
                .andExpect(jsonPath("$[2].id").value(4))
                .andExpect(jsonPath("$[2].nameNative").value("Schindler's List"))
                .andExpect(jsonPath("$[2].nameUkrainian").value("Список Шиндлера"))
                .andExpect(jsonPath("$[2].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[2].price").value(150.5))
                .andExpect(jsonPath("$[2].rating").value(8.8))
                .andExpect(jsonPath("$[2].yearOfRealise").value(1993))
                .andExpect(jsonPath("$[3].id").value(3))
                .andExpect(jsonPath("$[3].nameNative").value("Forrest Gump"))
                .andExpect(jsonPath("$[3].nameUkrainian").value("Форрест Гамп"))
                .andExpect(jsonPath("$[3].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[3].price").value(200.6))
                .andExpect(jsonPath("$[3].rating").value(8.6))
                .andExpect(jsonPath("$[3].yearOfRealise").value(1994))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get movies with sort by price desc")
    void testGetMoviesWithSortByPriceDesc() throws Exception {
        mockMvc.perform(get("/api/v1/movies?price=desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(3))
                .andExpect(jsonPath("$[0].nameNative").value("Forrest Gump"))
                .andExpect(jsonPath("$[0].nameUkrainian").value("Форрест Гамп"))
                .andExpect(jsonPath("$[0].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[0].price").value(200.6))
                .andExpect(jsonPath("$[0].rating").value(8.6))
                .andExpect(jsonPath("$[0].yearOfRealise").value(1994))
                .andExpect(jsonPath("$[1].id").value(4))
                .andExpect(jsonPath("$[1].nameNative").value("Schindler's List"))
                .andExpect(jsonPath("$[1].nameUkrainian").value("Список Шиндлера"))
                .andExpect(jsonPath("$[1].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[1].price").value(150.5))
                .andExpect(jsonPath("$[1].rating").value(8.8))
                .andExpect(jsonPath("$[1].yearOfRealise").value(1993))
                .andExpect(jsonPath("$[2].id").value(2))
                .andExpect(jsonPath("$[2].nameNative").value("The Green Mile"))
                .andExpect(jsonPath("$[2].nameUkrainian").value("Зелена миля"))
                .andExpect(jsonPath("$[2].picturePath").value("https://uk.wikipedia.org/wiki/%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F_%28%D1%84%D1%96%D0%BB%D1%8C%D0%BC%29#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Green_mile_(%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F).jpg"))
                .andExpect(jsonPath("$[2].price").value(134.67))
                .andExpect(jsonPath("$[2].rating").value(8.9))
                .andExpect(jsonPath("$[2].yearOfRealise").value(1999))
                .andExpect(jsonPath("$[3].id").value(1))
                .andExpect(jsonPath("$[3].nameNative").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[3].nameUkrainian").value("Втеча з Шовшенка"))
                .andExpect(jsonPath("$[3].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[3].price").value(123.45))
                .andExpect(jsonPath("$[3].rating").value(8.9))
                .andExpect(jsonPath("$[3].yearOfRealise").value(1994))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get movies with sort by price asc")
    void testGetMoviesWithSortByPriceAsc() throws Exception {
        mockMvc.perform(get("/api/v1/movies?price=asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nameNative").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[0].nameUkrainian").value("Втеча з Шовшенка"))
                .andExpect(jsonPath("$[0].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[0].price").value(123.45))
                .andExpect(jsonPath("$[0].rating").value(8.9))
                .andExpect(jsonPath("$[0].yearOfRealise").value(1994))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nameNative").value("The Green Mile"))
                .andExpect(jsonPath("$[1].nameUkrainian").value("Зелена миля"))
                .andExpect(jsonPath("$[1].picturePath").value("https://uk.wikipedia.org/wiki/%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F_%28%D1%84%D1%96%D0%BB%D1%8C%D0%BC%29#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Green_mile_(%D0%97%D0%B5%D0%BB%D0%B5%D0%BD%D0%B0_%D0%BC%D0%B8%D0%BB%D1%8F).jpg"))
                .andExpect(jsonPath("$[1].price").value(134.67))
                .andExpect(jsonPath("$[1].rating").value(8.9))
                .andExpect(jsonPath("$[1].yearOfRealise").value(1999))
                .andExpect(jsonPath("$[2].id").value(4))
                .andExpect(jsonPath("$[2].nameNative").value("Schindler's List"))
                .andExpect(jsonPath("$[2].nameUkrainian").value("Список Шиндлера"))
                .andExpect(jsonPath("$[2].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[2].price").value(150.5))
                .andExpect(jsonPath("$[2].rating").value(8.8))
                .andExpect(jsonPath("$[2].yearOfRealise").value(1993))
                .andExpect(jsonPath("$[3].id").value(3))
                .andExpect(jsonPath("$[3].nameNative").value("Forrest Gump"))
                .andExpect(jsonPath("$[3].nameUkrainian").value("Форрест Гамп"))
                .andExpect(jsonPath("$[3].picturePath").value("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B1%D0%B5%D0%B3_%D0%B8%D0%B7_%D0%A8%D0%BE%D1%83%D1%88%D0%B5%D0%BD%D0%BA%D0%B0#/media/%D0%A4%D0%B0%D0%B9%D0%BB:Movie_poster_the_shawshank_redemption.jpg"))
                .andExpect(jsonPath("$[3].price").value(200.6))
                .andExpect(jsonPath("$[3].rating").value(8.6))
                .andExpect(jsonPath("$[3].yearOfRealise").value(1994))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(status().isOk());
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test get 3 random movies")
    void getTreeRandomMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movies/random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").exists())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].nameNative").exists())
                .andExpect(jsonPath("$[*].nameUkrainian").exists())
                .andExpect(jsonPath("$[*].picturePath").exists())
                .andExpect(jsonPath("$[*].price").exists())
                .andExpect(jsonPath("$[*].rating").exists())
                .andExpect(jsonPath("$[*].yearOfRealise").exists())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(status().isOk());
    }
}
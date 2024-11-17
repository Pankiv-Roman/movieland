package com.pankiv.movieland.web.controller;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.pankiv.movieland.AbstractBaseITest;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
class AuthControllerTest extends AbstractBaseITest {

    @Autowired
    MockMvc mockMvc;

    String token;

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_scheme_history")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test login")
    void testLogin() throws Exception {
        SQLStatementCountValidator.reset();

        JSONObject requestJson = new JSONObject();
        requestJson.put("email", "ronald.reynolds66@example.com");
        requestJson.put("password", "poco");

        mockMvc.perform(postJson("/api/v1/auth/login", requestJson.toString())
                        .param("Authorization", "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.username").value("Дарлин Эдвардс"));
        assertSelectCount(3);
    }

    @Test
    @DataSet(value = "datasets/movie_and_genre_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_scheme_history")
    @ExpectedDataSet(value = "datasets/movie_and_genre_dataset.yml")
    @DisplayName("Test logout")
    void testLogoutWithLogin() throws Exception {
        SQLStatementCountValidator.reset();

        JSONObject requestJson = new JSONObject();
        requestJson.put("email", "ronald.reynolds66@example.com");
        requestJson.put("password", "poco");

        mockMvc.perform(postJson("/api/v1/auth/login", requestJson.toString())
                .param("Authorization", "Bearer " + this.token)
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete("/api/v1/auth/logout", requestJson.toString())
                        .param("Authorization", "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully logged out"));
        assertSelectCount(3);
    }

    private static @NotNull MockHttpServletRequestBuilder postJson(String url, String content) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }
}
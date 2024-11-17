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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import java.util.ArrayList;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
class ReviewControllerTest extends AbstractBaseITest {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Test
    @DataSet(value = "datasets/movie_user_country_genre_dataset.yml",
            cleanAfter = true, cleanBefore = true, skipCleaningFor = "flyway_scheme_history")
    @ExpectedDataSet(value = "datasets/movie_user_country_genre_dataset.yml")
    @DisplayName("Test add review with role USER")
    void testAddReviewWithRoleUser() throws Exception {
        SQLStatementCountValidator.reset();

        JSONObject loginJson = new JSONObject();
        loginJson.put("email", "ronald.reynolds66@example.com");
        loginJson.put("password", "poco");

        String response = mockMvc.perform(postJson("/api/v1/auth/login", loginJson.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonResponse = new JSONObject(response);
        token = jsonResponse.getString("token");

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("ronald.reynolds66@example.com", null, new ArrayList<>())
        );

        mockMvc.perform(postJson("/api/v1/review", "{\"text\":\"Great movie!\",\"movieId\":1}")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertSelectCount(5);
    }

    private static @NotNull MockHttpServletRequestBuilder postJson(String url, String content) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
    }
}

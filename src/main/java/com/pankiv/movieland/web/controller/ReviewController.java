package com.pankiv.movieland.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pankiv.movieland.dto.ReviewRequestDto;
import com.pankiv.movieland.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@RequestMapping("api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequestDto reviewRequest) {
        reviewService.addReview(reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
    }
}

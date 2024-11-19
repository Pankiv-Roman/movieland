package com.pankiv.movieland.service;

import com.pankiv.movieland.dto.ReviewRequestDto;

public interface ReviewService {
    void addReview(ReviewRequestDto reviewRequest);
}

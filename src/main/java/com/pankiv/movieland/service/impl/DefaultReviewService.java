package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.dto.ReviewRequestDto;
import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.entity.Review;
import com.pankiv.movieland.entity.User;
import com.pankiv.movieland.repository.MovieRepository;
import com.pankiv.movieland.repository.ReviewRepository;
import com.pankiv.movieland.repository.UserRepository;
import com.pankiv.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReviewService implements ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Override
    public void addReview(ReviewRequestDto reviewRequest) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        Movie movie = movieRepository.findById(reviewRequest.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + reviewRequest.getMovieId()));

        Review review = new Review();
        review.setMovie(movie);
        review.setText(reviewRequest.getText());
        review.setUser(user);

        reviewRepository.save(review);
    }
}

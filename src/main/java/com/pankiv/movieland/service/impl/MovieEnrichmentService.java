package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Country;
import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Review;
import com.pankiv.movieland.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
@AllArgsConstructor
@Slf4j
@Profile("Parallel")
public class MovieEnrichmentService {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final MovieService movieService;

    public void enrichMovieData(Long movieId) {
        Future<List<Genre>> genresFuture = executorService.submit(() -> movieService.getGenres(movieId));
        Future<List<Country>> countriesFuture = executorService.submit(() -> movieService.getCountries(movieId));
        Future<List<Review>> reviewsFuture = executorService.submit(() -> movieService.getReviews(movieId));

        try {
            List<Genre> genres = getResult(genresFuture);
            List<Country> countries = getResult(countriesFuture);
            List<Review> reviews = getResult(reviewsFuture);

            processGenres(genres);
            processCountries(countries);
            processReviews(reviews);

        } catch (Exception e) {
            log.error("Error processing data for movie with ID {}: {}", movieId, e.getMessage(), e);
        } finally {
            executorService.shutdown();
        }
    }

    private <T> @Nullable T getResult(@NotNull Future<T> future) throws ExecutionException, InterruptedException {
        try {
            return future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.warn("The task was canceled due to a timeout.");
            future.cancel(true);
            return null;
        }
    }

    private void processGenres(List<Genre> genres) {
        if (genres != null) {
            log.info("Get genres: {}", genres);
        } else {
            log.warn("No genres received.");
        }
    }

    private void processCountries(List<Country> countries) {
        if (countries != null) {
            log.info("Get countries: {}", countries);
        } else {
            log.warn("No countries received.");
        }
    }

    private void processReviews(List<Review> reviews) {
        if (reviews != null) {
            log.info("Get reviews: {}", reviews);
        } else {
            log.warn("No reviews received.");
        }
    }
}

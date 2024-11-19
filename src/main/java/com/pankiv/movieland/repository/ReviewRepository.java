package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.movie.id = :movieId")
    List<Review> findAllByMovieId(Long movieId);
}

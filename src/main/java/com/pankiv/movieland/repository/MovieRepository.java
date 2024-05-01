package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

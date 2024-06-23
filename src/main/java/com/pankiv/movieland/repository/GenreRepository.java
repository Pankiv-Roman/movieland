package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}

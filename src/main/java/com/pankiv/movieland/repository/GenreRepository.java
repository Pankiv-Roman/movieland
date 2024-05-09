package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}

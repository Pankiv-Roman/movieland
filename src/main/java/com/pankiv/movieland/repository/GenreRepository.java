package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
//    @Query("SELECT g FROM Genre g JOIN g.movies m WHERE m.id = :movieId")
//    List<Genre> findByMovieId(int movieId);
}
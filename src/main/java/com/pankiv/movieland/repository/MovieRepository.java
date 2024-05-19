package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Movie> findAllTreeRandom();

    @Query("SELECT m FROM Movie m WHERE m.genre.id = :genreId")
    List<Movie> findByGenreId(@Param("genreId") Long genreId);
}

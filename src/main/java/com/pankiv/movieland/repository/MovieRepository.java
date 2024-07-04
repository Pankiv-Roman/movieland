package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<Movie> findAllTreeRandom();

    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id = :genreId")
    List<Movie> findByGenreId(@Param("genreId") Long genreId);

    @Query(value = "SELECT * FROM movie ORDER BY rating DESC", nativeQuery = true)
    List<Movie> findAllBySortByRatingDesc();

    @Query(value = "SELECT * FROM movie ORDER BY price DESC", nativeQuery = true)
    List<Movie> findAllBySortByPriceDesc();

    @Query(value = "SELECT * FROM movie ORDER BY price ASC", nativeQuery = true)
    List<Movie> findAllBySortByPriceAsc();

}
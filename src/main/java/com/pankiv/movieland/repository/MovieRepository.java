package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "SELECT m FROM Movie m JOIN FETCH  m.genres")
    List<Movie> findAllMovies();

    @Query(value = "SELECT m From Movie m JOIN FETCH m.genres ORDER BY RANDOM() LIMIT 3")
    List<Movie> findAllTreeRandom();

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.genres g WHERE g.id = :genreId")
    List<Movie> findByGenreId(@Param("genreId") Long genreId);

    @Query(value = "SELECT m FROM Movie m JOIN FETCH m.genres ORDER BY m.rating DESC")
    List<Movie> findAllBySortByRatingDesc();

    @Query(value = "SELECT m FROM Movie m JOIN FETCH m.genres ORDER BY m.price DESC")
    List<Movie> findAllBySortByPriceDesc();

    @Query(value = "SELECT m FROM Movie m JOIN FETCH m.genres ORDER BY m.price ASC")
    List<Movie> findAllBySortByPriceAsc();
}
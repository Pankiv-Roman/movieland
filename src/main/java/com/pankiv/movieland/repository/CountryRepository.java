package com.pankiv.movieland.repository;

import com.pankiv.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT c FROM Country c JOIN c.movies m WHERE m.id = :movieId")
    List<Country> findAllByMovieId(Long movieId);
}


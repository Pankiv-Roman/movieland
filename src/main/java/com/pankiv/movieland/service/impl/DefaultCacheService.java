package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCacheService {

    private final GenreRepository genreRepository;
    private List<Genre> genreCache = new ArrayList<>();

    @Autowired
    public DefaultCacheService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        updateGenreCache();
    }

    public List<Genre> getAllGenres() {
        return new ArrayList<>(genreCache);
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    private void updateGenreCache() {
        List<Genre> genresFromDB = genreRepository.findAll();
        if (genresFromDB != null) {
            genreCache = genresFromDB;
        }
    }
}

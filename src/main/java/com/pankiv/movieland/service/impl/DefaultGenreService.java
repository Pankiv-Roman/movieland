package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.repository.GenreRepository;
import com.pankiv.movieland.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGenreService implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}

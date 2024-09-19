package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.dto.GenreDto;
import com.pankiv.movieland.mapper.GenreMapper;
import com.pankiv.movieland.repository.GenreRepository;
import com.pankiv.movieland.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreMapper.toDtoList(genreRepository.findAll());
    }
}

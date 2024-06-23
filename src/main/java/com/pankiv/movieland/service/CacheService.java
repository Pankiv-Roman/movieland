package com.pankiv.movieland.service;

import com.pankiv.movieland.entity.Genre;

import java.util.List;

public interface CacheService {

    List<Genre> getAllGenresFromCache();
}

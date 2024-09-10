package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.repository.GenreRepository;
import com.pankiv.movieland.service.GenreCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class DefaultGenreCacheService implements GenreCacheService {

    private final GenreRepository genreRepository;
    private final List<Genre> genreCache = new CopyOnWriteArrayList<>();
    private final AtomicBoolean cacheInitialized = new AtomicBoolean(false);

    @Autowired
    public DefaultGenreCacheService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenresFromCache() {
        if (cacheInitialized.compareAndSet(false, true)) {
            initializeCache();
        }
        return Collections.unmodifiableList(genreCache);
    }

    private void initializeCache() {
        if (genreCache.isEmpty()) {
            log.info("Initializing genre cache");
            List<Genre> genresFromDB = genreRepository.findAll();
            genreCache.addAll(genresFromDB);
            log.info("Genre cache initialized with {} entries", genreCache.size());
        }
    }

    @Scheduled(fixedDelay = 4 * 60 * 1000)
    private void updateGenreCache() {
        log.info("Refreshing genre cache");
        List<Genre> genresFromDB = genreRepository.findAll();
        genreCache.clear();
        genreCache.addAll(genresFromDB);
        log.info("Genre cache updated with {} new entries", genreCache.size());
    }
}

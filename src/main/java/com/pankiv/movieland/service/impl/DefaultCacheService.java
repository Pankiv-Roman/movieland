package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.repository.GenreRepository;
import com.pankiv.movieland.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class DefaultCacheService implements CacheService {

    private final GenreRepository genreRepository;
    private final List<Genre> genreCache = new CopyOnWriteArrayList<>();
    private final AtomicBoolean cacheInitialized = new AtomicBoolean(false);

    @Autowired
    public DefaultCacheService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenresFromCache () {
        if (cacheInitialized.compareAndSet(false, true)) {
            initializeCache();
        }
        return new ArrayList<>(genreCache);
    }

    private synchronized void initializeCache() {
        if (genreCache.isEmpty()) {
            log.info("Initializing genre cache");
            List<Genre> genresFromDB = genreRepository.findAll();
            genreCache.addAll(genresFromDB);
            log.info("Genre cache initialized with {} entries", genreCache.size());
        }
    }

    @Scheduled(cron = "${cache.movie.cron}")
    private synchronized void updateGenreCache() {
        log.info("Refreshing genre cache");
        List<Genre> genresFromDB = genreRepository.findAll();
        List<Genre> newGenres = genresFromDB.stream()
                .filter(genre -> !genreCache.contains(genre))
                .toList();
        genreCache.addAll(newGenres);
        log.info("Genre cache updated with {} new entries", newGenres.size());
    }
}

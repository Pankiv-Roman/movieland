package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, CountryMapper.class, ReviewMapper.class})
public interface MovieFullDataMapper {
    MovieFullDataDto toDto(Movie movie);

    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "countries", source = "countries")
    @Mapping(target = "reviews", source = "reviews")
    Movie toEntity(MovieFullDataDto movieFullDataDto);
}

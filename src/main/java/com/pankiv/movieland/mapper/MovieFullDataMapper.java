package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.MovieFullDataDto;
import com.pankiv.movieland.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieFullDataMapper {
    MovieFullDataDto toDto(Movie movie);

    List<MovieFullDataDto> toDtoList(List<Movie> movies);

    Movie toEntity(MovieFullDataDto movieFullDataDto);

    List<Movie> toEntityList(List<MovieFullDataDto> movieFullDataDtoList);
}

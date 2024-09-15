package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.MovieDto;
import com.pankiv.movieland.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toDto(Movie movie);

    List<MovieDto> toDtoList(List<Movie> movies);

    Movie toEntity(MovieDto movieDto);

    List<Movie> toEntityList(List<MovieDto> movieDtoList);
}

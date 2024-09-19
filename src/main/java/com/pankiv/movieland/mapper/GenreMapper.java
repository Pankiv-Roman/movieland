package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.GenreDto;
import com.pankiv.movieland.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    List<GenreDto> toDtoList(List<Genre> genres);

    Genre toEntity(GenreDto genreDto);

    List<Genre> toEntityList(List<GenreDto> genresDtoList);
}

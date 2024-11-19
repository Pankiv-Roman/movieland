package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.CountryDto;
import com.pankiv.movieland.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(Country country);

    List<CountryDto> toDtoList(List<Country> countries);

    Country toEntity(CountryDto countryDto);
}

package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.ReviewDto;
import com.pankiv.movieland.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ReviewMapper {

    @Mapping(target = "user", source = "user")  // Мапимо об'єкт User до UserDto
    ReviewDto toDto(Review review);

    @Mapping(target = "user", source = "user")
    Review toEntity(ReviewDto reviewDto);
}
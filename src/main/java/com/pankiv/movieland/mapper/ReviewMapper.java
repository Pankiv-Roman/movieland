package com.pankiv.movieland.mapper;

import com.pankiv.movieland.dto.ReviewDto;
import com.pankiv.movieland.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDto toDto(Review review);

    List<ReviewDto> toDtoList(List<Review> reviews);

    Review toEntity(ReviewMapper reviewDto);

    List<Review> toEntityList(List<ReviewDto> reviewsDtoList);

}

package com.pankiv.movieland.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
    private Integer movieId;
    private String text;
}

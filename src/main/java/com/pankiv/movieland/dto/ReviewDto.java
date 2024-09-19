package com.pankiv.movieland.dto;

import com.pankiv.movieland.entity.Movie;
import com.pankiv.movieland.entity.User;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private int id;
    private String text;
    private User user;
    private Movie movie;
}

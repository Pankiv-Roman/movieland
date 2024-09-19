package com.pankiv.movieland.dto;

import com.pankiv.movieland.entity.Country;
import com.pankiv.movieland.entity.Genre;
import com.pankiv.movieland.entity.Review;
import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFullDataDto {
    private Integer id;

    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRelease;
    private String description;
    private Double rating;
    private Double price;
    private String picturePath;
    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;
}

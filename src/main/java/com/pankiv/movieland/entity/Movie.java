package com.pankiv.movieland.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "movieBuilder")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nameUkrainian;
    private String nameNative;
    private Integer yearOfRealise;
    private Double rating;
    private Double price;
    private String picturePath;

    @ManyToOne()
    @JoinColumn(name = "genre_id")
    @JsonBackReference
    private Genre genre;

}

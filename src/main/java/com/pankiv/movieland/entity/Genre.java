package com.pankiv.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "genreBuilder" )

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnoreProperties("genres")
    private List<Movie> movies = new ArrayList<>();
}

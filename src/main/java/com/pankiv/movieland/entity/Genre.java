package com.pankiv.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnoreProperties("genres")
    private List<Movie> movies = new ArrayList<>();

    public void setGenre(String test) {
        //checking the cache in the test
    }

}

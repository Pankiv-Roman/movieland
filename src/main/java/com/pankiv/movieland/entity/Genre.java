package com.pankiv.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "genre")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String genre;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private List<Movie> movies = new ArrayList<>();

    public void setGenre(String test) {
        //checking the cache in the test
    }
}

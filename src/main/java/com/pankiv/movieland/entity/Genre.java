package com.pankiv.movieland.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "genreBuilder" )

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre", orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Movie> movies = new ArrayList<>();
}

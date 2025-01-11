package mk.ecode.movies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

    public Movie() {
    }

    public Movie(String name, String description, Double rating, Genre genre, Director director) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.genre = genre;
        this.director = director;
        this.votes = 0;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Double rating;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    private Director director;

    private Integer votes = 0;

}

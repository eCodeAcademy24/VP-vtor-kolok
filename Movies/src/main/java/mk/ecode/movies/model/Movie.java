package mk.ecode.movies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Movie {
    private String title;
    private String summary;
    private double rating;
}

package mk.ecode.artists.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album {

    private Long id;
    private String name;
    private String genre;
    private int releaseYear;

    public Album(String name, String genre, int releaseYear) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}

package mk.ecode.artists.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Song {

    private Long id;
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    private List<Artist> artists;
    private Album album;

    public Song(String trackId, String title, String genre, int releaseYear, Album album) {
        this.id = (long) (Math.random() * 1000);
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        artists = new ArrayList<>();
        this.album = album;
    }
}

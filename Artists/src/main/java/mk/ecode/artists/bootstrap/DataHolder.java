package mk.ecode.artists.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();

    @PostConstruct
    public void init() {
        artists.add(new Artist(1L, "First 1", "Last 1", "Bio 1"));
        artists.add(new Artist(2L, "First 2", "Last 2", "Bio 2"));
        artists.add(new Artist(3L, "First 3", "Last 3", "Bio 3"));
        artists.add(new Artist(4L, "First 4", "Last 4", "Bio 4"));
        artists.add(new Artist(5L, "First 5", "Last 5", "Bio 5"));
        artists.add(new Artist(6L, "First 6", "Last 6", "Bio 6"));
        artists.add(new Artist(7L, "First 7", "Last 7", "Bio 7"));
        artists.add(new Artist(8L, "First 8", "Last 8", "Bio 8"));
        artists.add(new Artist(9L, "First 9", "Last 9", "Bio 9"));
        artists.add(new Artist(10L, "First 10", "Last 10", "Bio 10"));

        songs.add(new Song("1", "Song 1", "Rap", 2002));
        songs.add(new Song("2", "Song 2", "Hip-Hop", 2003));
        songs.add(new Song("3", "Song 3", "Pop", 2004));
        songs.add(new Song("4", "Song 4", "Rap", 2005));
        songs.add(new Song("5", "Song 5", "Hip-Hop", 2006));
        songs.add(new Song("6", "Song 6", "Pop", 2007));
        songs.add(new Song("7", "Song 7", "Jazz", 2008));
        songs.add(new Song("8", "Song 8", "Rap", 2009));
        songs.add(new Song("9", "Song 9", "Pop", 2010));
        songs.add(new Song("10", "Song 10", "Hip-Hop", 2001));
    }
}

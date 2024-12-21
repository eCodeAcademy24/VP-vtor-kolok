package mk.ecode.artists.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ecode.artists.model.Album;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Album> albums = new ArrayList<>();
    public static List<Artist> artists = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();

    @PostConstruct
    public void init() {
        Album album1 = new Album("Album 1", "Genre 1", 2002);
        Album album2 = new Album("Album 2", "Genre 2", 2002);
        Album album3 = new Album("Album 3", "Genre 3", 2003);
        Album album4 = new Album("Album 4", "Genre 4", 2004);
        Album album5 = new Album("Album 5", "Genre 5", 2005);
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);
        albums.add(album4);
        albums.add(album5);

        artists.add(new Artist("First 1", "Last 1", "Bio 1"));
        artists.add(new Artist("First 2", "Last 2", "Bio 2"));
        artists.add(new Artist("First 3", "Last 3", "Bio 3"));
        artists.add(new Artist("First 4", "Last 4", "Bio 4"));
        artists.add(new Artist("First 5", "Last 5", "Bio 5"));
        artists.add(new Artist("First 6", "Last 6", "Bio 6"));
        artists.add(new Artist("First 7", "Last 7", "Bio 7"));
        artists.add(new Artist("First 8", "Last 8", "Bio 8"));
        artists.add(new Artist("First 9", "Last 9", "Bio 9"));
        artists.add(new Artist("First 10", "Last 10", "Bio 10"));

        songs.add(new Song("1", "Song 1", "Rap", 2002, album1));
        songs.add(new Song("2", "Song 2", "Hip-Hop", 2003, album2));
        songs.add(new Song("3", "Song 3", "Pop", 2004, album3));
        songs.add(new Song("4", "Song 4", "Rap", 2005, album4));
        songs.add(new Song("5", "Song 5", "Hip-Hop", 2006, album5));
        songs.add(new Song("6", "Song 6", "Pop", 2007, album5));
        songs.add(new Song("7", "Song 7", "Jazz", 2008, album4));
        songs.add(new Song("8", "Song 8", "Rap", 2009, album3));
        songs.add(new Song("9", "Song 9", "Pop", 2010, album2));
        songs.add(new Song("10", "Song 10", "Hip-Hop", 2001, album1));
    }
}

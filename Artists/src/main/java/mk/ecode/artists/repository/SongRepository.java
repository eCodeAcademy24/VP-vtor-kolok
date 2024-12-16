package mk.ecode.artists.repository;

import mk.ecode.artists.bootstrap.DataHolder;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository {
    public List<Song> findAll() {
        return DataHolder.songs;
    }

    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream()
                .filter(song -> song.getTrackId().equals(trackId))
                .findFirst()
                .orElseThrow();
    }

    public Song addArtistToSong(Artist artist, Song song) {
        song.getArtists().add(artist);
        return song;
    }
}

package mk.ecode.artists.repository;

import mk.ecode.artists.bootstrap.DataHolder;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void save(Song song) {
        boolean found = DataHolder.songs.stream()
                .anyMatch(s -> s.getId().equals(song.getId()));

        if (found) {
            DataHolder.songs = DataHolder.songs.stream()
                    .map(s -> s.getId().equals(song.getId()) ? song : s)
                    .collect(Collectors.toList());
        } else {
            DataHolder.songs.add(song);
        }
    }

    public Optional<Song> findById(Long songId) {
        return DataHolder.songs.stream()
                .filter(song -> song.getId().equals(songId))
                .findFirst();
    }

    public void delete(Song song) {
        DataHolder.songs.remove(song);
    }
}

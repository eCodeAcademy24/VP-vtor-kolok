package mk.ecode.artists.service;

import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;

import java.util.List;

public interface SongService {

    Song findById(Long id);

    List<Song> listSongs();

    Song addArtistToSong(Long artistId, Long songId);

    Song findByTrackId(String trackId);

    void create(String trackId, String title, String genre, int releaseYear, List<Long> artistsId);

    void update(Long id, String trackId, String title, String genre, int releaseYear, List<Long> artistsId);

    void delete(Long songId);
}

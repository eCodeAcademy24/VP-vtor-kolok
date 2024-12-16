package mk.ecode.artists.service;

import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();

    Song addArtistToSong(Long artistId, String trackId);

    Song findByTrackId(String trackId);
}

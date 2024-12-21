package mk.ecode.artists.service;

import mk.ecode.artists.model.Song;

import java.util.List;

public interface SongService {

    Song findById(Long songId);

    List<Song> listSongs();

    void addArtistToSong(Long artistId, Long songId);

    void create(String trackId, String title, String genre, int releaseYear, Long albumId);

    void update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId);

    void delete(Long id);

    List<Song> findAllByAlbumId(Long albumId);
}

package mk.ecode.artists.service;

import mk.ecode.artists.model.Song;

import java.util.List;

public interface SongService {

    Song findById(Long id);

    List<Song> listSongs();

    void create(String trackId, String title, String genre, int releaseYear, Long albumId);

    void update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId);

    void delete(Long songId);

    List<Song> findAllByAlbum_Id(Long albumId);
}

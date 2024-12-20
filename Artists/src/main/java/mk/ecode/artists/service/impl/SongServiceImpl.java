package mk.ecode.artists.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.model.Album;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import mk.ecode.artists.repository.jpa.SongJpaRepository;
import mk.ecode.artists.service.AlbumService;
import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongJpaRepository songJpaRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Override
    public List<Song> listSongs() {
        return songJpaRepository.findAll();
    }

    @Override
    public Song findById(Long songId) {
        return songJpaRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
    }

    @Override
    public void create(String trackId, String title, String genre, int releaseYear, Long albumId) {
        Song song = new Song();
        Album album = albumService.findById(albumId);

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songJpaRepository.save(song);
    }

    @Override
    public void update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId) {
        Song song = findById(id);
        Album album = albumService.findById(albumId);

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songJpaRepository.save(song);
    }

    @Override
    public void delete(Long songId) {
        Song song = findById(songId);
        List<Artist> artists = artistService.findAllBySong_Id(songId);

        if (!artists.isEmpty()) {
            artistService.deleteAll(artists);
        }

        songJpaRepository.delete(song);
    }

    @Override
    public List<Song> findAllByAlbum_Id(Long albumId) {
        return songJpaRepository.findAllByAlbum_Id(albumId);
    }


}

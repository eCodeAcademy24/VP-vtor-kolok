package mk.ecode.artists.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.model.Album;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import mk.ecode.artists.repository.SongRepository;
import mk.ecode.artists.repository.jpa.SongJpaRepository;
import mk.ecode.artists.service.AlbumService;
import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongJpaRepository songJpaRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Override
    public Song findById(Long songId) {
        return songJpaRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found!"));
    }

    @Override
    public List<Song> listSongs() {
        return songJpaRepository.findAll();
    }

    @Override
    public void addArtistToSong(Long artistId, Long songId) {
        Artist artist = artistService.findById(artistId);
        Song song = findById(songId);
        songRepository.addArtistToSong(artist, song);
    }

    @Override
    public void create(String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album album = albumService.findById(albumId);
        Song song = new Song();

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songJpaRepository.save(song);
    }

    @Override
    public void update(Long id, String trackId, String title, String genre, int releaseYear, Long albumId) {
        Album album = albumService.findById(albumId);
        Song song = findById(id);

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        songJpaRepository.save(song);
    }

    @Override
    public void delete(Long id) {
        Song song = findById(id);
        songJpaRepository.delete(song);
    }

    @Override
    public List<Song> findAllByAlbumId(Long albumId) {
        return songJpaRepository.findAllByAlbum_Id(albumId);
    }
}

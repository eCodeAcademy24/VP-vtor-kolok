package mk.ecode.artists.service.impl;

import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import mk.ecode.artists.repository.ArtistRepository;
import mk.ecode.artists.repository.SongRepository;
import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistService artistService;

    public SongServiceImpl(SongRepository songRepository, ArtistService artistService) {
        this.songRepository = songRepository;
        this.artistService = artistService;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song findById(Long songId) {
        return songRepository.findById(songId).orElseThrow(RuntimeException::new);
    }

    @Override
    public Song addArtistToSong(Long artistId, Long songId) {
        Artist artist = artistService.findById(artistId);
        Song song = findById(songId);
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void create(String trackId, String title, String genre, int releaseYear, List<Long> artistsId) {
        Song song = new Song();
        List<Artist> artists = artistService.findAllById(artistsId);

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setArtists(artists);

        songRepository.save(song);
    }

    @Override
    public void update(Long id, String trackId, String title, String genre, int releaseYear, List<Long> artistsId) {
        Song song = findById(id);
        List<Artist> artists = artistService.findAllById(artistsId);

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setArtists(artists);

        songRepository.save(song);
    }

    @Override
    public void delete(Long songId) {
        Song song = findById(songId);
        songRepository.delete(song);
    }


}

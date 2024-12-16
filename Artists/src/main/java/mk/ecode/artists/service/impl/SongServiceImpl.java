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
    public Song addArtistToSong(Long artistId, String trackId) {
        Artist artist = artistService.findById(artistId);
        Song song = songRepository.findByTrackId(trackId);
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }
}

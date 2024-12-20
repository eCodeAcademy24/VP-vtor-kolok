package mk.ecode.artists.service;

import mk.ecode.artists.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> listArtists();

    Artist findById(Long id);

    List<Artist> findAllById(List<Long> artistsId);

    List<Artist> findAllBySong_Id(Long songId);

    void deleteAll(List<Artist> artists);
}

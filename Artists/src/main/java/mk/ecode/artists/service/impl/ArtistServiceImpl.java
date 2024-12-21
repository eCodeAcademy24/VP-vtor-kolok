package mk.ecode.artists.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.model.Artist;
import mk.ecode.artists.repository.jpa.ArtistJpaRepository;
import mk.ecode.artists.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistJpaRepository artistJpaRepository;

    @Override
    public List<Artist> listArtists() {
        return artistJpaRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found!"));
    }
}

package mk.ecode.artists.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.model.Album;
import mk.ecode.artists.repository.jpa.AlbumJpaRepository;
import mk.ecode.artists.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumJpaRepository albumJpaRepository;

    @Override
    public List<Album> findAll() {
        return albumJpaRepository.findAll();
    }

    @Override
    public Album findById(Long albumId) {
        return albumJpaRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found!"));
    }
}

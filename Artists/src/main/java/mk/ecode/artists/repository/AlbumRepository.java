package mk.ecode.artists.repository;

import mk.ecode.artists.bootstrap.DataHolder;
import mk.ecode.artists.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumRepository {

    public List<Album> findAll() {
        return DataHolder.albums;
    }

    public Optional<Album> findById(Long albumId) {
        return DataHolder.albums.stream()
                .filter(album -> album.getId().equals(albumId))
                .findFirst();
    }
}

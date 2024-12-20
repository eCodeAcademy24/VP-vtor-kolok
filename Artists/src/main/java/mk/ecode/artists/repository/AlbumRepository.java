package mk.ecode.artists.repository;

import mk.ecode.artists.bootstrap.DataHolder;
import mk.ecode.artists.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepository {

    public List<Album> findAll() {
        return DataHolder.albums;
    }
}

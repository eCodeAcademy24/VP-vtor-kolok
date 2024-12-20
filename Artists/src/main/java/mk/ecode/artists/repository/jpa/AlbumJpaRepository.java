package mk.ecode.artists.repository.jpa;

import mk.ecode.artists.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumJpaRepository extends JpaRepository<Album, Long> {
}

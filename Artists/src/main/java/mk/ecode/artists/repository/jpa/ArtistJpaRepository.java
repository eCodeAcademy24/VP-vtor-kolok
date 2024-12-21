package mk.ecode.artists.repository.jpa;

import mk.ecode.artists.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistJpaRepository extends JpaRepository<Artist, Long> {
}

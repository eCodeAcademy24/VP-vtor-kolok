package mk.ecode.artists.repository.jpa;

import mk.ecode.artists.model.Artist;
import mk.ecode.artists.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistJpaRepository extends JpaRepository<Artist, Long> {
    List<Artist> song(Song song);

    List<Artist> findAllBySong_Id(Long songId);
}

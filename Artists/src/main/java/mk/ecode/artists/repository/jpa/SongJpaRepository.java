package mk.ecode.artists.repository.jpa;

import mk.ecode.artists.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongJpaRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByAlbum_Id(Long albumId);
}

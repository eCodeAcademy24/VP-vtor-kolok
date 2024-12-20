package mk.ecode.events.repository.jpa;

import mk.ecode.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventJpaRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByLocation_Id(Long locationId);

    List<Event> findByNameContainingAndPopularityScoreGreaterThanEqual(String searchText, Double popularityScore);

    List<Event> findByNameContaining(String searchText);

    List<Event> findByPopularityScoreGreaterThanEqual(Double popularityScore);

    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}

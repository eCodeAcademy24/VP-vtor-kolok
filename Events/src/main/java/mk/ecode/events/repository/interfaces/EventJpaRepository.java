package mk.ecode.events.repository.interfaces;

import mk.ecode.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventJpaRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByLocation_Id(Long locationId);

    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    List<Event> findByNameContainingAndPopularityScoreGreaterThanEqual(String searchText, Double aDouble);

    List<Event> findByNameContaining(String searchText);

    List<Event> findByPopularityScoreGreaterThanEqual(Double aDouble);
}

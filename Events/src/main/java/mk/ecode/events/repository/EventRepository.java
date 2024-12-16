package mk.ecode.events.repository;

import jakarta.annotation.PostConstruct;
import mk.ecode.events.bootstrap.DataHolder;
import mk.ecode.events.model.Event;
import mk.ecode.events.model.Location;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> listAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase()) || event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Event> findByNameContainingAndPopularityScoreGreaterThanEqual(String searchText, Double popularityScore) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().toLowerCase().contains(searchText.toLowerCase()) && event.getPopularityScore() >= popularityScore)
                .collect(Collectors.toList());
    }

    public List<Event> findByNameContaining(String searchText) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Event> findByPopularityScoreGreaterThanEqual(Double popularityScore) {
        return DataHolder.events.stream()
                .filter(event -> event.getPopularityScore() >= popularityScore)
                .collect(Collectors.toList());
    }

    public void saveEvent(String name, String description, Double popularityScore, Location location) {
        Event event = new Event(
                name,
                description,
                popularityScore,
                location
        );
        DataHolder.events.add(event);
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }


    public void editEvent(Long id, String name, String description, Double popularityScore, Location location) {
        Event event = DataHolder.events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow();

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);
    }

    public void delete(Long id) {
        DataHolder.events.removeIf(event -> event.getId().equals(id));
    }
}

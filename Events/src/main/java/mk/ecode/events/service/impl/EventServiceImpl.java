package mk.ecode.events.service.impl;

import mk.ecode.events.model.Event;
import mk.ecode.events.model.Location;
import mk.ecode.events.repository.EventRepository;
import mk.ecode.events.repository.interfaces.EventJpaRepository;
import mk.ecode.events.service.EventService;
import mk.ecode.events.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    //    private final EventRepository eventRepository;
    private final LocationService locationService;
    private final EventJpaRepository eventJpaRepository;

    public EventServiceImpl(LocationService locationService, EventJpaRepository eventJpaRepository) {
//        this.eventRepository = eventRepository;
        this.locationService = locationService;
        this.eventJpaRepository = eventJpaRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventJpaRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        if (text.startsWith(" ") || text.startsWith(",") || text.startsWith(".")
                || text.startsWith("!") || text.startsWith("?")) {
            text = text.substring(1);
        } else if (text.endsWith(" ") || text.endsWith(",") || text.endsWith(".")
                || text.endsWith("!") || text.endsWith("?")) {
            text = text.substring(0, text.length() - 1);
        }

        return eventJpaRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
    }

    @Override
    public List<Event> filter(String searchText, String popularityScore) {
        if (!searchText.isEmpty() && !popularityScore.isEmpty()) {
            return eventJpaRepository.findByNameContainingAndPopularityScoreGreaterThanEqual(searchText, Double.valueOf(popularityScore));
        } else if (!searchText.isEmpty()) {
            return eventJpaRepository.findByNameContaining(searchText);
        } else if (!popularityScore.isEmpty()) {
            return eventJpaRepository.findByPopularityScoreGreaterThanEqual(Double.valueOf(popularityScore));
        }

        return listAll();
    }

    @Override
    public void saveEvent(String name, String description, Double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event event = new Event();

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);

        eventJpaRepository.save(event);
    }

    @Override
    public Event findById(Long id) {
        return eventJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public void editEvent(Long id, String name, String description, Double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId);
        Event event = findById(id);

        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(location);

        eventJpaRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        Event event = findById(id);

        eventJpaRepository.delete(event);
    }

    @Override
    public List<Event> findByLocationId(Long locationId) {
        return eventJpaRepository.findAllByLocation_Id(locationId);
    }
}

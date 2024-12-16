package mk.ecode.events.service;

import mk.ecode.events.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> listAll();

    List<Event> searchEvents(String text);

    List<Event> filter(String searchText, String popularityScore);

    void saveEvent(String name, String description, Double popularityScore, Long locationId);

    Event findById(Long id);

    void editEvent(Long id, String name, String description, Double popularityScore, Long locationId);

    void delete(Long id);

    List<Event> findByLocationId(Long locationId);
}

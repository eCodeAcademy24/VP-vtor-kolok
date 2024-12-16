package mk.ecode.events.repository;

import mk.ecode.events.bootstrap.DataHolder;
import mk.ecode.events.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {

    public List<Location> findAll() {
        return DataHolder.locations;
    }

    public Optional<Location> findById(Long locationId) {
        return DataHolder.locations.stream()
                .filter(location -> location.getId().equals(locationId))
                .findFirst();
    }
}

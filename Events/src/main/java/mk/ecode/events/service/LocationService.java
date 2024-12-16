package mk.ecode.events.service;

import mk.ecode.events.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> findAll();

    Location findById(Long locationId);
}

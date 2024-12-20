package mk.ecode.events.service.impl;

import mk.ecode.events.model.Location;
import mk.ecode.events.repository.jpa.LocationJpaRepository;
import mk.ecode.events.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

//    private final LocationRepository locationRepository;
    private final LocationJpaRepository locationJpaRepository;

    public LocationServiceImpl(LocationJpaRepository locationJpaRepository) {
        this.locationJpaRepository = locationJpaRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationJpaRepository.findAll();
    }

    @Override
    public Location findById(Long locationId) {
        return locationJpaRepository.findById(locationId).orElseThrow();
    }
}

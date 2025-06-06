package mk.ecode.reservations.service.impl;

import mk.ecode.reservations.model.Hotel;
import mk.ecode.reservations.model.exceptions.InvalidHotelIdException;
import mk.ecode.reservations.repository.HotelRepository;
import mk.ecode.reservations.service.HotelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(InvalidHotelIdException::new);
    }

    @Override
    public List<Hotel> listAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel create(String name) {
        Hotel hotel = new Hotel();
        hotel.setName(name);

        return hotelRepository.save(hotel);
    }
}

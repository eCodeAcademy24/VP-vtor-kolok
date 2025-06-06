package mk.ecode.reservations.service;

import mk.ecode.reservations.model.Hotel;
import mk.ecode.reservations.model.exceptions.InvalidHotelIdException;

import java.util.List;

public interface HotelService {

    /**
     * @param id The id of the hotel that we want to obtain
     * @return The hotel with the appropriate id
     * @throws InvalidHotelIdException when there is no hotel with the given id
     */
    Hotel findById(Long id);

    /**
     * @return List of all hotel in the database
     */
    List<Hotel> listAll();

    /**
     * This method is used to create a new hotel, and save it in the database.
     *
     * @param name
     * @return The hotel that is created. The id should be generated when the hotel is created.
     */
    Hotel create(String name);
}

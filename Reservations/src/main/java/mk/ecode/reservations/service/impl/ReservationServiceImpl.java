package mk.ecode.reservations.service.impl;

import mk.ecode.reservations.model.Hotel;
import mk.ecode.reservations.model.Reservation;
import mk.ecode.reservations.model.RoomType;
import mk.ecode.reservations.model.exceptions.InvalidReservationIdException;
import mk.ecode.reservations.repository.ReservationRepository;
import mk.ecode.reservations.service.HotelService;
import mk.ecode.reservations.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static mk.ecode.reservations.service.FieldFilterSpecification.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelService hotelService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HotelService hotelService) {
        this.reservationRepository = reservationRepository;
        this.hotelService = hotelService;
    }

    @Override
    public List<Reservation> listAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(InvalidReservationIdException::new);
    }

    @Override
    public Reservation create(String guestName, LocalDate dateCreated, Integer daysOfStay, RoomType roomType, Long hotelId) {
        Reservation reservation = new Reservation();
        Hotel hotel = hotelService.findById(hotelId);

        reservation.setGuestName(guestName);
        reservation.setDateCreated(dateCreated);
        reservation.setDaysOfStay(daysOfStay);
        reservation.setRoomType(roomType);
        reservation.setHotel(hotel);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Long id, String guestName, LocalDate dateCreated, Integer daysOfStay, RoomType roomType, Long hotelId) {
        Reservation reservation = findById(id);

        reservation.setGuestName(guestName);
        reservation.setDateCreated(dateCreated);
        reservation.setDaysOfStay(daysOfStay);
        reservation.setRoomType(roomType);

        // you need to find the hotel by the id and set it to the reservation
        Hotel hotel = hotelService.findById(hotelId);
        reservation.setHotel(hotel);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation delete(Long id) {
        Reservation reservation = findById(id);
        reservationRepository.delete(reservation);

        return reservation;
    }

    @Override
    public Reservation extendStay(Long id) {
        Reservation reservation = findById(id);
        reservation.setDaysOfStay(reservation.getDaysOfStay() + 1);

        return reservationRepository.save(reservation);
    }

    @Override
    public Page<Reservation> findPage(String guestName, RoomType roomType, Long hotel, int pageNum, int pageSize) {
        Specification<Reservation> specification = Specification
                .where(filterContainsText(Reservation.class, "guestName", guestName))
                .and(filterEqualsV(Reservation.class, "roomType", roomType))
                .and(filterEquals(Reservation.class, "hotel.id", hotel));

        return reservationRepository.findAll(specification, PageRequest.of(pageNum, pageSize));
    }
}

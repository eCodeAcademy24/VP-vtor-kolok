package mk.ecode.reservations.config;

import jakarta.annotation.PostConstruct;
import mk.ecode.reservations.model.RoomType;
import mk.ecode.reservations.service.HotelService;
import mk.ecode.reservations.service.ReservationService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private final HotelService hotelService;
    private final ReservationService reservationService;

    public DataInitializer(HotelService hotelService, ReservationService reservationService) {
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }


    private RoomType randomize(int i) {
        if (i % 2 == 0) return RoomType.SINGLE;
        return RoomType.DOUBLE;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.hotelService.create("Hotel: " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.reservationService.create("Reservation: " + i, LocalDate.now().minusYears(25 + i), 0, this.randomize(i), this.hotelService.listAll().get((i - 1) % 5).getId());
        }
    }
}

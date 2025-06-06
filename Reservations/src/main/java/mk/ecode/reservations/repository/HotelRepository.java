package mk.ecode.reservations.repository;

import mk.ecode.reservations.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

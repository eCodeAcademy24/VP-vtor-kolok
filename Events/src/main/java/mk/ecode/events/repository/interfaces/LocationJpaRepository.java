package mk.ecode.events.repository.interfaces;

import mk.ecode.events.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationJpaRepository extends JpaRepository<Location, Long> {
}

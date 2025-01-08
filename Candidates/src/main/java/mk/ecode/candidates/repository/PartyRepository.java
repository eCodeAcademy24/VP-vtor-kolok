package mk.ecode.candidates.repository;

import mk.ecode.candidates.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {
}

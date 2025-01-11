package mk.ecode.movies.repository;

import mk.ecode.movies.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}

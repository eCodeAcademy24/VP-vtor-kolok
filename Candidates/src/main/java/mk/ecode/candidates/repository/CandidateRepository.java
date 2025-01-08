package mk.ecode.candidates.repository;

import mk.ecode.candidates.model.Candidate;
import mk.ecode.candidates.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findAllByGenderAndDateOfBirthBefore(Gender gender, LocalDate localDate);

    List<Candidate> findAllByGender(Gender gender);

    List<Candidate> findAllByDateOfBirthBefore(LocalDate localDate);
}

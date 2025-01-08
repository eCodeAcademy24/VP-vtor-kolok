package mk.ecode.discussions.repository;

import mk.ecode.discussions.model.Discussion;
import mk.ecode.discussions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    List<Discussion> findAllByParticipantsContainingAndDueDateBefore(User byId, LocalDate localDate);

    List<Discussion> findAllByParticipantsContaining(User byId);

    List<Discussion> findAllByDueDateBefore(LocalDate localDate);
}

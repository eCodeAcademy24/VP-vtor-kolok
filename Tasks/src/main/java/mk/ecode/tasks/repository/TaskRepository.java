package mk.ecode.tasks.repository;

import mk.ecode.tasks.model.Task;
import mk.ecode.tasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssigneesContainingAndDueDateBefore(User user, LocalDate localDate);

    List<Task> findByAssigneesContaining(User byId);

    List<Task> findByDueDateBefore(LocalDate localDate);
}

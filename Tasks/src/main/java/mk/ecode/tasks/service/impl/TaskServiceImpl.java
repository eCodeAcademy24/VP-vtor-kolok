package mk.ecode.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.tasks.model.Task;
import mk.ecode.tasks.model.TaskCategory;
import mk.ecode.tasks.model.User;
import mk.ecode.tasks.model.exceptions.InvalidTaskIdException;
import mk.ecode.tasks.repository.TaskRepository;
import mk.ecode.tasks.service.TaskService;
import mk.ecode.tasks.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new InvalidTaskIdException(id));
    }

    @Override
    public Task create(String title, String description, TaskCategory category, List<Long> assignees, LocalDate dueDate) {
        Task task = new Task();
        List<User> users = assignees.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setAssignees(users);
        task.setDueDate(dueDate);

        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, String title, String description, TaskCategory category, List<Long> assignees) {
        Task task = findById(id);
        List<User> users = assignees.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        task.setTitle(title);
        task.setDescription(description);
        task.setCategory(category);
        task.setAssignees(users);

        return taskRepository.save(task);
    }

    @Override
    public Task delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public Task markDone(Long id) {
        Task task = findById(id);
        task.setDone(true);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> filter(Long assigneeId, Integer lessThanDayBeforeDueDate) {
        if (assigneeId != null && lessThanDayBeforeDueDate != null) {
            return taskRepository.findByAssigneesContainingAndDueDateBefore(
                    userService.findById(assigneeId),
                    LocalDate.now().plusDays(lessThanDayBeforeDueDate)
            );
        } else if (assigneeId != null) {
            return taskRepository.findByAssigneesContaining(userService.findById(assigneeId));
        } else if (lessThanDayBeforeDueDate != null) {
            return taskRepository.findByDueDateBefore(LocalDate.now().plusDays(lessThanDayBeforeDueDate));
        }

        return listAll();
    }
}

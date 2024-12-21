package mk.ecode.tasks.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Task {

    public Task(String title, String description, TaskCategory category, List<User> assignees, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.assignees = assignees;
        this.dueDate = dueDate;
    }

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dueDate;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskCategory category;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> assignees;

    private Boolean done = false;

}

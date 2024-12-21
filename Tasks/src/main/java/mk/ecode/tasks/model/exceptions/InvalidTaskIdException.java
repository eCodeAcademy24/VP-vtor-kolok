package mk.ecode.tasks.model.exceptions;

public class InvalidTaskIdException extends RuntimeException {
    public InvalidTaskIdException(Long taskId) {
        super("Task with id=" + taskId + " does not exist");
    }
}

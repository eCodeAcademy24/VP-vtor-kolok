package mk.ecode.tasks.model.exceptions;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(Long userId) {
        super("User with id=" + userId + " does not exist");
    }
}

package mk.ecode.discussions.model.exceptions;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(Long id) {
        super("Invalid user id: " + id);
    }
}

package mk.ecode.discussions.model.exceptions;

public class InvalidDiscussionIdException extends RuntimeException {

    public InvalidDiscussionIdException(Long id) {
        super("Invalid discussion id: " + id);
    }
}

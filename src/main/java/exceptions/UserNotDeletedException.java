package exceptions;

import entities.User;

public class UserNotDeletedException extends Throwable {

    private final User user;

    public UserNotDeletedException(User user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return STR. "User: \{ this.user } not deleted" ;
    }
}

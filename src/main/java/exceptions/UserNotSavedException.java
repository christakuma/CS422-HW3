package exceptions;

import entities.User;

public class UserNotSavedException extends Throwable {

    private final User user;


    public UserNotSavedException(User user) {
        this.user = user;
    }

    @Override
    public String getMessage() {
        return STR. "User:\{ this.user } saving resulted in a problem" ;
    }
}

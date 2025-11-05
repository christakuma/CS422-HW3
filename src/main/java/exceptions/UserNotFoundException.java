package exceptions;

public class UserNotFoundException extends Throwable {

    private final int id;

    public UserNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return STR. "User corresponding to id \{ this.id } not found" ;
    }
}

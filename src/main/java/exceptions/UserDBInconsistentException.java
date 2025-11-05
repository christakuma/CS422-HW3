package exceptions;

public class UserDBInconsistentException extends Throwable {

    @Override
    public String getMessage() {
        return "The Database UserDB is in an inconsistent state" + super.getMessage();
    }
}

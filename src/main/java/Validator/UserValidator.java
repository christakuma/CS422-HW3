package Validator;

import entities.User;

public class UserValidator {

    public boolean validateUser(User user) {
        return isValidFirstName(user.getFirstName()) &&
                isValidLastName(user.getLastName());
    }

    private boolean isValidLastName(String lastName) {
        return lastName.length() > 1 && lastName.length() < 20;
    }

    private boolean isValidFirstName(String firstName) {
        return firstName.length() > 1 && firstName.length() < 20;
    }
}

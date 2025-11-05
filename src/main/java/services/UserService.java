package services;

import entities.User;
import exceptions.*;

import java.util.Collection;

public interface UserService {

    boolean isRegisteredUser(int id);

    boolean isAdminUser(int id);

    User createUser(String firstName, String lastName, String role) throws InvalidUserInfoException, UserNotSavedException, UserDBInconsistentException;

    User updateUser(int id, String firstName, String lastName, String role) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException, InvalidUserInfoException;

    User deleteUser(int id) throws UserNotFoundException, UserNotSavedException, UserNotDeletedException, UserDBInconsistentException;

    Collection<User> getAllUsers();

}

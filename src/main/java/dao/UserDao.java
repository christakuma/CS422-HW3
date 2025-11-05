package dao;

import entities.Role;
import entities.User;
import exceptions.UserDBInconsistentException;
import exceptions.UserNotDeletedException;
import exceptions.UserNotFoundException;
import exceptions.UserNotSavedException;

import java.util.Collection;

public interface UserDao {

    User saveUser(User user) throws UserNotSavedException, UserDBInconsistentException;

    User getUserById(int id) throws UserNotFoundException;

    Collection<User> findUsersByRole(Role role);

    User updateUser(User user) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException;

    User deleteUser(User user) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException, UserNotDeletedException;

    Collection<User> getAllUsers();

}

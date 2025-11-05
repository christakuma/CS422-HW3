package services;

import Validator.UserValidator;
import dao.UserDao;
import entities.Role;
import entities.User;
import exceptions.*;

import java.util.Collection;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserValidator userValidator;

    public UserServiceImpl(UserDao userDao, UserValidator userValidator) {
        this.userDao = userDao;
        this.userValidator = userValidator;
    }

    @Override
    public boolean isRegisteredUser(int id) {
        try {
            User user = userDao.getUserById(id);
            if (user != null) {
                return true;
            }
        } catch (UserNotFoundException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean isAdminUser(int id) {
        try {
            User user = userDao.getUserById(id);
            if (user != null && user.getRole().equals(Role.ADMIN)) {
                return true;
            }
        } catch (UserNotFoundException e) {
            return false;
        }
        return false;
    }

    @Override
    public User createUser(String firstName, String lastName, String role) throws InvalidUserInfoException, UserNotSavedException, UserDBInconsistentException {
        User user = new User(firstName, lastName, Role.valueOf(role));
        if (!userValidator.validateUser(user)) {
            throw new InvalidUserInfoException(user);
        }
        return userDao.saveUser(user);
    }

    @Override
    public User updateUser(int id, String firstName, String lastName, String role) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException, InvalidUserInfoException {
        User user = new User(id, firstName, lastName, Role.valueOf(role));
        if (!userValidator.validateUser(user)) {
            throw new InvalidUserInfoException(user);
        }
        return userDao.updateUser(user);
    }

    @Override
    public User deleteUser(int id) throws UserNotFoundException, UserNotSavedException, UserNotDeletedException, UserDBInconsistentException {
        User user = userDao.getUserById(id);
        return userDao.deleteUser(user);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}

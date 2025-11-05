package dao;

import database.UserDB;
import entities.Role;
import entities.User;
import exceptions.UserDBInconsistentException;
import exceptions.UserNotDeletedException;
import exceptions.UserNotFoundException;
import exceptions.UserNotSavedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final UserDB userDB;

    public UserDaoImpl(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public User saveUser(User user) throws UserNotSavedException, UserDBInconsistentException {
        Collection<User> savedUsers = UserDB.saveUsers(List.of(user));
        if (savedUsers.isEmpty()) throw new UserNotSavedException(user);
        if (savedUsers.size() > 1) throw new UserDBInconsistentException();
        return savedUsers.iterator().next();
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        for (User user : userDB.getUsers()) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public Collection<User> findUsersByRole(Role role) {
        List<User> usersMatchingRole = new ArrayList<>();
        for (User user : userDB.getUsers()) {
            if (user.getRole().equals(role)) {
                usersMatchingRole.add(user);
            }
        }
        return usersMatchingRole;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException {
        getUserById(user.getId());
        return saveUser(user);
    }

    @Override
    public User deleteUser(User user) throws UserNotFoundException, UserNotSavedException, UserDBInconsistentException, UserNotDeletedException {
        User userToDelete = getUserById(user.getId());
        Collection<User> deletedUsers = userDB.deleteUsers(List.of(userToDelete));
        if (deletedUsers.isEmpty()) throw new UserNotDeletedException(user);
        if (deletedUsers.size() > 1) throw new UserDBInconsistentException();
        return deletedUsers.iterator().next();

    }

    @Override
    public Collection<User> getAllUsers() {
        return userDB.getUsers();
    }
}

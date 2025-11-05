import Validator.UserValidator;
import dao.UserDao;
import dao.UserDaoImpl;
import database.UserDB;
import entities.User;
import exceptions.*;
import services.UserService;
import services.UserServiceImpl;

public class Main {

    private static UserService buildUserService() {
        UserDB userDB = new UserDB();
        UserDao userDao = new UserDaoImpl(userDB);
        UserValidator validator = new UserValidator();
        return new UserServiceImpl(userDao, validator);
    }

    private static void printAllUsers(String lastOperation, UserService userService) {
        System.out.println(STR. "\nAfter operation-\{ lastOperation }:" );
        System.out.println("-----------");
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        UserService userService = buildUserService();
        try {
            /* Create two users: 1 Customer and 1 Admin */
            userService.createUser("Joe", "Smith", "CUSTOMER");
            userService.createUser("Root", "Admin", "ADMIN");
            /* list Users */
            printAllUsers("Adding two users", userService);
            /* Update the Customer */
            userService.updateUser(1, "Subu", "Kandaswamy", "CUSTOMER");
            /* list Users */
            printAllUsers("Updating first user", userService);
            /* delete the Customer */
            userService.deleteUser(1);
            /* list Users */
            printAllUsers("Deleting first user", userService);
        } catch (InvalidUserInfoException | UserNotSavedException | UserDBInconsistentException e) {
            System.out.println(e.getMessage());
        } catch (UserNotFoundException | UserNotDeletedException e) {
            throw new RuntimeException(e);
        }
    }
}

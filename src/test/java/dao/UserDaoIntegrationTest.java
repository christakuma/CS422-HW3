package dao;

import Validator.UserValidator;
import dao.UserDaoImpl;
import database.UserDB; // We need this to mock it
import entities.Role;
import entities.User;
import exceptions.InvalidUserInfoException;
import exceptions.UserDBInconsistentException;
import exceptions.UserNotFoundException;
import exceptions.UserNotSavedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UserService;
import services.UserServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

// Integration test to verify interaction between UserServiceImpl and UserDaoImpl, specifically for user update
@ExtendWith(MockitoExtension.class)
public class UserDaoIntegrationTest {

    @Mock
    private UserValidator mockValidator; // Mocked UserValidator, not part of the tested pair

    @Mock
    private UserDB mockUserDB; // Mocked UserDB, not part of the tested pair

    private UserDao userDao;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl(mockUserDB);
        userService = new UserServiceImpl(userDao, mockValidator);
    }

    @Test
    void testUpdateUser_WhenUserValid_DoesCallDaoUpdate() throws Exception,
            UserNotFoundException, InvalidUserInfoException, UserNotSavedException, UserDBInconsistentException {

        int userId = 1;
        String newFirstName = "John";
        String newLastName = "Doe";
        String role = "CUSTOMER";

        User userToUpdate = new User(userId, newFirstName, newLastName, Role.CUSTOMER);
        User existingUser = new User(userId, "OldFirstName", "OldLastName", Role.CUSTOMER);

        when(mockValidator.validateUser(any(User.class))).thenReturn(true);
        when(mockUserDB.getUsers()).thenReturn(List.of(existingUser));

        // Static testing
        try (MockedStatic<UserDB> mockedUserDBStatic = mockStatic(UserDB.class)) {
            // Mock the save
            mockedUserDBStatic.when(() -> UserDB.saveUsers(any(List.class))).thenReturn(List.of(userToUpdate));

            User updatedUser = userService.updateUser(userId, newFirstName, newLastName, role); // Exceptions handled by test signature

            assertNotNull(updatedUser);
            assertEquals(userId, updatedUser.getId());
            assertEquals(newFirstName, updatedUser.getFirstName());
            assertEquals(newLastName, updatedUser.getLastName());

            verify(mockValidator, times(1)).validateUser(argThat(user ->
                    user.getId() == userId &&
                            user.getFirstName().equals(newFirstName) &&
                            user.getLastName().equals(newLastName) &&
                            user.getRole() == Role.CUSTOMER
            ));

            verify(mockUserDB).getUsers();

            // Verify static method call
            // Cast list to List<User> (intelliJ keeps assuming it's a Collection, causing issues with .get())
            mockedUserDBStatic.verify(() -> UserDB.saveUsers(argThat((List<User> list) ->
                    list.size() == 1 &&
                    list.get(0).getId() == userId &&
                    list.get(0).getFirstName().equals(newFirstName) &&
                    list.get(0).getLastName().equals(newLastName)
            )));
        }
    }
}
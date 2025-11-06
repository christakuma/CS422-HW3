package Validator;

import dao.UserDao;
import entities.Role;
import entities.User;
import exceptions.InvalidUserInfoException;
import exceptions.UserDBInconsistentException;
import exceptions.UserNotSavedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.UserService;
import services.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


// Integration test to verify interaction between UserServiceImpl and UserValidator, specifically for user creation
@ExtendWith(MockitoExtension.class)
class UserValidatorIntegrationTest {

    @Mock
    private UserDao mockUserDao; // Mocked UserDao, not part of the tested pair

    private UserValidator userValidator;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userValidator = new UserValidator();
        userService = new UserServiceImpl(mockUserDao, userValidator);
    }

    @Test
    void testCreateUser_WhenUserValid_DoesCallValidatorAndPassesValidation()
            throws InvalidUserInfoException, UserNotSavedException, UserDBInconsistentException {

        String firstName = "John";
        String lastName = "Doe";
        String role = "CUSTOMER";

        User expectedUser = new User(1, firstName, lastName, Role.CUSTOMER);

        when(mockUserDao.saveUser(any(User.class))).thenReturn(expectedUser);

        User createdUser = userService.createUser(firstName, lastName, role);

        assertNotNull(createdUser);
        assertEquals(1, createdUser.getId());
        assertEquals(firstName, createdUser.getFirstName());
        assertEquals(lastName, createdUser.getLastName());
        assertEquals(Role.CUSTOMER, createdUser.getRole());

        verify(mockUserDao, times(1)).saveUser(argThat(user ->
                user.getFirstName().equals(firstName) &&
                user.getLastName().equals(lastName) &&
                user.getRole() == Role.CUSTOMER
        ));
    }
}
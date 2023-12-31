package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import ba.unsa.etf.rpr.exceptions.UserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Class used for testing UserManager class
 * @author Emina Peljto
 */
public class UserManagerTest {
    private final UserManager userManager = Mockito.mock(UserManager.class);
    private User user;

    @BeforeEach
    void setup()
    {
        user = newUser(1,"user1","Userpass1");
    }

    @Test
    public void validateSignUpTest() throws UserException
    {
        Mockito.doCallRealMethod().when(userManager).validateSignUp(Mockito.any(),Mockito.any());
        UserException e = Assertions.assertThrows(UserException.class, () -> {
            userManager.validateSignUp("Userpass1","Userpass2");
        });
        Assertions.assertEquals("Passwords do not match.", e.getMessage());

        e = Assertions.assertThrows(UserException.class, () -> {
            userManager.validateSignUp("userpass","userpass");
        });
        Assertions.assertEquals("Password needs to contain at least one uppercase letter and one number.", e.getMessage());
    }

    @Test
    public void getUserTest() throws RecordStoreException
    {
        User newUser = new User();
        newUser.setUsername("newuser");
        newUser.setPassword("newUser1");
        Mockito.when(userManager.getUser(user.getUsername(), user.getPassword())).thenReturn(newUser);
        User nuser = userManager.getUser(user.getUsername(), user.getPassword());
        Assertions.assertEquals(nuser, newUser);
    }

    public User newUser(int id, String username, String password)
    {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}

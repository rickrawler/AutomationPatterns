import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPageTest extends BaseTest {

    private static final Properties property = new Properties();

    @Test
    void login() {
        LoginPage loginPage = new LoginPage();
        UserPage userPage = loginPage
                .open()
                .setLogin(property.getProperty("botLogin"))
                .setPassword(property.getProperty("botPassword"))
                .signIn();
        assertEquals(property.getProperty("botName"), userPage.getUsername());
    }

    @AfterAll
    static void quit() {
        LoginPage.quit();
    }
}
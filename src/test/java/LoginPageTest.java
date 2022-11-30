import Utils.PropertiesReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPageTest {

    private static final Properties property = PropertiesReader.initProperties();

    @Test
    void login() {
        LoginPage loginPage = new LoginPage();
        UserPage userPage = loginPage
                .sendLogin(property.getProperty("botLogin"))
                .sendPassword(property.getProperty("botPassword"))
                .signIn();
        assertEquals(property.getProperty("botName"), userPage.getUsername());
    }

    @AfterAll
    static void quit() {
        LoginPage.quit();
    }
}
import Utils.PageHasNotLoadedException;
import Utils.PropertiesReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Properties;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest extends BaseTest {

    private static final Properties property = PropertiesReader.initProperties();

    @ParameterizedTest
    @MethodSource("loginDataProvider")
    void testCorrectLogin(String login, String password, String expectedName) throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver);
        UserPage userPage = loginPage
                .open()
                .sendLogin(login)
                .sendPassword(password)
                .signIn();
        assertEquals(expectedName, userPage.getUsername());
    }

    private static Stream<Arguments> loginDataProvider() {
        return Stream.of(
                Arguments.of(property.getProperty("botLogin"), property.getProperty("botPassword"), property.getProperty("botName")),
                Arguments.of(property.getProperty("myLogin"), property.getProperty("myPassword"), property.getProperty("myName"))
        );
    }

    @Test
    void testIncorrectLogin() throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver);
        UserPage userPage = loginPage
                .open()
                .sendLogin(property.getProperty("incorrectBotLogin"))
                .sendPassword(property.getProperty("botPassword"))
                .signIn();
        assertNull(userPage);
    }

    @Test
    void testIncorrectPassword() throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver);
        UserPage userPage = loginPage
                .open()
                .sendLogin(property.getProperty("botLogin"))
                .sendPassword(property.getProperty("incorrectBotPassword"))
                .signIn();
        assertNull(userPage);
    }

    @Test
    void testLinkToMail() throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver);
        String mailPageUrl = loginPage
                .open()
                .goToMail()
                .getUrl();
        assert mailPageUrl.startsWith("https://account.mail.ru");
    }

    @Test
    void testLanguageChanging() throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver)
                .open()
                .changeLanguage("English");
        String[] labels = {loginPage.getLoginLabelText(), loginPage.getPasswordLabelText()};
        assertAll(
                "labels",
                () -> assertEquals("Mobile phone or e-mail address", labels[0]),
                () -> assertEquals("Password", labels[1])
        );
    }

    @Test
    void testLoginToAnotherUser() throws PageHasNotLoadedException {
        LoginPage loginPage = new LoginPage(driver)
                .open()
                .sendLogin(property.getProperty("botLogin"))
                .sendPassword(property.getProperty("botPassword"))
                .signIn()
                .loginToAnotherProfile()
                .sendLogin(property.getProperty("myLogin"))
                .sendPassword(property.getProperty("myPassword"))
                .signIn()
                .loginToAnotherProfile();

        HashSet<String> profileNames = loginPage.getLoggedProfilesNames();

        assertAll(
                "profileNames",
                () -> assertTrue(profileNames.contains(property.getProperty("myName"))),
                () -> assertTrue(profileNames.contains(property.getProperty("botName"))),
                () -> assertTrue(profileNames.contains("Другой профиль"))
        );
    }

}
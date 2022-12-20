import Utils.PropertiesReader;
import Utils.User;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class UserPageTest extends BaseTest {
    private static final Properties property = PropertiesReader.initProperties();

    @Test
    void testMainButtonActionChanging() {
        User bot = new User(property.getProperty("botLogin"), property.getProperty("botPassword"),
                property.getProperty("botFirstname"), property.getProperty("botLastname"));
        User me = new User(property.getProperty("myLogin"), property.getProperty("myPassword"),
                property.getProperty("myFirstname"), property.getProperty("myLastname"));
        UserPage botProfile = new LoginPage(driver)
                .open()
                .login(bot)
                .getUserPage()
                .openSettings()
                .selectMainBtnAction("Сделать подарок")
                .returnToFeed();
        UserPage myPage = botProfile
                .loginToAnotherProfile()
                .login(me)
                .findUser(bot.getFirstName() + " " + bot.getLastName());

        boolean hasAction = myPage.mainButtonHasAction("Сделать подарок");

        myPage.loginToAlreadyLoggedProfile(bot.getFirstName() + " " + bot.getLastName())
                .openSettings()
                .selectMainBtnAction("Нет");

        assertTrue(hasAction);
    }
}
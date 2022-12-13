import Utils.PageHasNotLoadedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends BasePage {

    private static final By USERNAME_FIELD_LOCATOR = By.xpath("//div[@class=\"tico ellip\"]");
    private static final By OPTIONS_LOCATOR = By.xpath("//div[@class=\"toolbar_dropdown_w h-mod\"]");
    private static final By LOGIN_TO_ANOTHER_PROFILE_BTN_LOCATOR = By.xpath("//a[text()=\"Войти в другой профиль\"]");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void checkIfPageLoaded() throws PageHasNotLoadedException {
        LeftSideMenu menu = new LeftSideMenu(driver);
        if (!menu.isLoaded()) {
            throw new PageHasNotLoadedException("No left side menu at user's page");
        }
    }

    public UserPage getUserPage() {
        try {
            checkIfPageLoaded();
        } catch (PageHasNotLoadedException exception) {
            return null;
        }
        return this;
    }

    public String getUsername() {
        WebElement usernameField = driver.findElement(USERNAME_FIELD_LOCATOR);
        return usernameField.getText();
    }

    public LoginPage loginToAnotherProfile() {
        WebElement options = driver.findElement(OPTIONS_LOCATOR);
        if (options.isDisplayed()) {
            options.click();
            WebElement logToAnProfileBtn = driver.findElement(LOGIN_TO_ANOTHER_PROFILE_BTN_LOCATOR);
            logToAnProfileBtn.click();
            return new LoginPage(driver);
        }
        return null;
    }
}

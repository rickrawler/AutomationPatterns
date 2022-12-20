import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserPage extends BasePage {

    private static final By USERNAME_FIELD_LOCATOR = By.xpath("//div[@class=\"tico ellip\"]");
    private static final By OPTIONS_LOCATOR = By.xpath("//div[@class=\"toolbar_dropdown_w h-mod\"]");
    private static final By SETTINGS_LOCATOR = By.xpath(
            "//a[@class=\"u-menu_a \" and contains(text(), \"Настройки\")]");
    private static final By SEARCH_LOCATOR = By.name("st.query");

    private static final By LOGIN_TO_ANOTHER_PROFILE_BTN_LOCATOR = By
            .xpath("//a[text()=\"Войти в другой профиль\"]");

    private static final By MAIN_BUTTON_LOCATOR = By.xpath(
            "//li[@class=\"u-menu_li __hl __hla __custom\"]/a[@class=\"u-menu_a \" and @href]");


    public UserPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void checkIfPageLoaded() {
        new LeftSideMenu(driver);
    }

    public UserPage getUserPage() {
        checkIfPageLoaded();
        return this;
    }

    public String getUsername() {
        driverWrapper.waitUntilVisible(USERNAME_FIELD_LOCATOR, 1);
        WebElement usernameField = driver.findElement(USERNAME_FIELD_LOCATOR);
        return usernameField.getText();
    }

    public LoginPage loginToAnotherProfile() {
        driverWrapper.waitUntilClickable(OPTIONS_LOCATOR, 1);
        WebElement options = driver.findElement(OPTIONS_LOCATOR);
        options.click();
        driverWrapper.waitUntilClickable(LOGIN_TO_ANOTHER_PROFILE_BTN_LOCATOR, 1);
        WebElement logToAnProfileBtn = driver.findElement(LOGIN_TO_ANOTHER_PROFILE_BTN_LOCATOR);
        logToAnProfileBtn.click();
        return new LoginPage(driver);
    }

    public UserPage loginToAlreadyLoggedProfile(String profileName) {
        driverWrapper.waitUntilClickable(OPTIONS_LOCATOR, 1);
        WebElement options = driver.findElement(OPTIONS_LOCATOR);
        options.click();
        By profileLocator = By.xpath(
                String.format("//div[@class=\"toolbar_accounts-user_name\" and text() = \"%s\"]", profileName));
        driverWrapper.waitUntilClickable(profileLocator, 3);
        WebElement anotherLoggedProfile = driver.findElement(profileLocator);
        anotherLoggedProfile.click();
        return new UserPage(driver).getUserPage();
    }

    public SettingsPage openSettings() {
        driverWrapper.waitUntilClickable(USERNAME_FIELD_LOCATOR, 1);
        WebElement usernameField = driver.findElement(USERNAME_FIELD_LOCATOR);
        usernameField.click();
        driverWrapper.waitUntilClickable(SETTINGS_LOCATOR, 1);
        WebElement settingsTab = driver.findElement(SETTINGS_LOCATOR);
        settingsTab.click();
        return new SettingsPage(driver);
    }

    public UserPage findUser(String query) {
        driverWrapper.waitUntilClickable(SEARCH_LOCATOR, 1);
        WebElement search = driver.findElement(SEARCH_LOCATOR);
        search.click();
        search.sendKeys(query);
        By queryLocator = By.xpath(String.format("//div[contains(@class, \"card-caption\") and text()= \"%s\"]", query));
        driverWrapper.waitUntilClickable(queryLocator, 3);
        WebElement queryOption = driver.findElement(queryLocator);
        queryOption.click();
        return new UserPage(driver);
    }

    public boolean mainButtonHasAction(String action) {
        driverWrapper.waitLocatedElementWithText(MAIN_BUTTON_LOCATOR, action, 1);
        return true;
    }
}

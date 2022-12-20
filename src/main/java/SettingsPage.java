import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SettingsPage extends BasePage {

    private static final By MY_PAGE_TAB_LOCATOR = By.xpath(
            "//div[@class = \"tico null\" and contains(text(), \"Моя страничка\")]");

    private static final By ACTIONS_SELECT_LOCATOR = By.name("st.button1action");

    private static final By SAVE_BTN_LOCATOR = By.name("button_save_settings");

    private static final By FEED_LOCATOR = By.xpath("//a[@href=\"/feed\" and text()= \"Лента\"]");


    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public SettingsPage selectMainBtnAction(String action) {
        driverWrapper.waitUntilClickable(MY_PAGE_TAB_LOCATOR, 1);
        WebElement myPageTab = driver.findElement(MY_PAGE_TAB_LOCATOR);
        myPageTab.click();
        driverWrapper.waitUntilVisible(ACTIONS_SELECT_LOCATOR, 1);
        Select actions = new Select(driver.findElement(ACTIONS_SELECT_LOCATOR));
        actions.selectByVisibleText(action);
        driverWrapper.waitUntilClickable(SAVE_BTN_LOCATOR, 1);
        WebElement saveButton = driver.findElement(SAVE_BTN_LOCATOR);
        saveButton.click();
        return this;
    }

    public UserPage returnToFeed() {
        driverWrapper.waitUntilClickable(FEED_LOCATOR, 1);
        WebElement feedTab = driver.findElement(FEED_LOCATOR);
        feedTab.click();
        return new UserPage(driver);
    }
}

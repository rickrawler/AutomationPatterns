package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoggedProfileWrapper {

    public static By EVERY_LOGGED_PROFILE_LOCATOR = By.xpath("//div[@class=\"user-profile-redesign_user-name\"]");
    private final WebElement root;
    private final WebDriver driver;

    public LoggedProfileWrapper(WebDriver driver, WebElement root) {
        this.root = root;
        this.driver = driver;
    }

    public List<LoggedProfileWrapper> getAllLoggedProfiles() {
        assert this.root.isDisplayed() : "No visible profiles";
        assert this.root.findElements(EVERY_LOGGED_PROFILE_LOCATOR).isEmpty() : "No logged profiles";
        return this.root
                .findElements(EVERY_LOGGED_PROFILE_LOCATOR)
                .stream()
                .map(newRoot -> new LoggedProfileWrapper(driver, newRoot))
                .toList();
    }

    public List<String> getLoggedProfilesNames() {
        assert this.root.isDisplayed() : "No visible profiles";
        assert !this.root.findElements(EVERY_LOGGED_PROFILE_LOCATOR).isEmpty() : "No logged profiles";
        return this.root
                .findElements(EVERY_LOGGED_PROFILE_LOCATOR)
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}

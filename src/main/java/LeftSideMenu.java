import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftSideMenu {

    private static final By LEFT_SIDE_MENU_LOCATOR = By.xpath(
            "//div[@class=\"nav-side __navigation __user-main\"]");

    private WebDriver driver;

    public LeftSideMenu(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.findElement(LEFT_SIDE_MENU_LOCATOR).isDisplayed();
    }

}

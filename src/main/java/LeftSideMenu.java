import Utils.DriverWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeftSideMenu {
    private static final By ALL_ELEMENTS_LOCATOR = By.xpath(
            "//div[contains(@class, \"__user-main\")]/div[contains(@class, \"nav-side_i-w\")]");

    private final WebDriver driver;

    private final DriverWrapper driverWrapper;

    public LeftSideMenu(WebDriver driver) {
        this.driver = driver;
        this.driverWrapper = new DriverWrapper(driver);
        checkIfItLoaded();
    }

    public void checkIfItLoaded() {
        driverWrapper.waitUntilVisible(driver.findElements(ALL_ELEMENTS_LOCATOR), 3);
    }
}

import Utils.DriverWrapper;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public WebDriver driver;
    public DriverWrapper driverWrapper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.driverWrapper = new DriverWrapper(driver);
    }

    public void checkIfPageLoaded() {
    }
}

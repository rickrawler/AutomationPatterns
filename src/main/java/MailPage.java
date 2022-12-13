import org.openqa.selenium.WebDriver;

public class MailPage extends BasePage {

    public MailPage(WebDriver driver) {
        super(driver);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}

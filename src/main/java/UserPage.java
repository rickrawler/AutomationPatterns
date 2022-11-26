import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserPage extends BasePage {

    static ChromeDriver driver;

    private WebElement usernameField = driver.findElement(By.xpath("//div[@class=\"tico ellip\"]"));

    public String getUsername() {
        return usernameField.getText();
    }
}

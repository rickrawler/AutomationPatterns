import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    private static final Properties property = new Properties();
    private static final String currentDir = System.getProperty("user.dir");

    public static ChromeDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    public static void setupPathToDriversExe() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(currentDir + "\\" + "conf.properties");
            property.load(fis);
            String driverPath = property.getProperty("driverPath");
            System.setProperty("webdriver.chrome.driver", driverPath);
        } catch (IOException e) {
            System.err.println("No config file. The latest version of Chrome Driver will be used");
        }
    }
}

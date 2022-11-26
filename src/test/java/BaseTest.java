import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    private static final Properties property = new Properties();
    private static final String currentDir = System.getProperty("user.dir");

    //public static ChromeDriver driver;
    static void setup() {
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

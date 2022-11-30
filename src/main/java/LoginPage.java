import Utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class LoginPage extends BasePage {

    private static final Properties property = PropertiesReader.initProperties();

    private String url;

    private final WebElement loginForm;

    private final WebElement passwordForm;

    private final WebElement signInButton;

    private WebElement signInWithQrCodeButton;

    private WebElement canNotSignInButton;

    private WebElement signUpButton;


    public LoginPage() {
        super();
        this.url = property.getProperty("LoginPageUrl");
        driver.get(this.url);
        this.loginForm = driver.findElement(By.name("st.email"));
        this.passwordForm = driver.findElement(By.name("st.password"));
        this.signInButton = driver.findElement(
                By.xpath("//input[contains(@class, \"button-pro\")]"));
        this.signInWithQrCodeButton = driver.findElement(
                By.xpath("//a[@aria-label=\"Войти по QR-коду\"]"));
        this.canNotSignInButton = driver.findElement(
                By.xpath("//div[@class=\"recovery-link\"]"));
        this.signUpButton = driver.findElement(
                By.xpath("//a[@class=\"button-pro __sec mb-3x\"]"));
    }


    public LoginPage open() {
        driver.get(url);
        return this;
    }

    public static void quit() {
        driver.quit();
    }

    public LoginPage sendLogin(String login) {
        loginForm.sendKeys(login);
        return this;
    }

    public LoginPage sendPassword(String password) {
        passwordForm.sendKeys(password);
        return this;
    }

    public UserPage signIn() {
        signInButton.click();
        return new UserPage();
    }

    public AccessRecoveryPage restoreAccess() {
        canNotSignInButton.click();
        return new AccessRecoveryPage();
    }

    public LoginPage signInWithQrCode() {
        signInWithQrCodeButton.click();
        return this;
    }

    public RegisterPage signUp() {
        signUpButton.click();
        return new RegisterPage();
    }


}

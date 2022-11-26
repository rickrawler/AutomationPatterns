import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends BasePage {

    private final String url = "https://ok.ru/";
    public static ChromeDriver driver;

    public LoginPage() {
        open();
    }

    private LoginPageToolbar toolbar = new LoginPageToolbar();

    private WebElement loginForm = driver.findElement(By.name("st.email"));

    private WebElement passwordForm = driver.findElement(By.name("st.password"));

    private WebElement signInButton = driver.findElement(
            By.xpath("//input[contains(@class, \"button-pro\")]"));
/*
    private WebElement signInWithQrCodeButton = driver.findElement(
            By.xpath("//a[@aria-label=\"Войти по QR-коду\"]"));

    private WebElement canNotSignInButton = driver.findElement(
            By.xpath("//div[@class=\"recovery-link\"]"));

    private WebElement signUpButton = driver.findElement(
            By.xpath("//a[@class=\"button-pro __sec mb-3x\"]"));
*/

    public LoginPage open() {
        driver.get(url);
        return this;
    }

    public static void quit() {
        driver.quit();
    }

    public LoginPage setLogin(String login) {
        loginForm.sendKeys(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordForm.sendKeys(password);
        return this;
    }

    public UserPage signIn() {
        signInButton.click();
        return new UserPage();
    }
/*
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

 */
}

import Utils.PageHasNotLoadedException;
import Utils.PropertiesReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class LoginPage extends BasePage {

    private static final Properties property = PropertiesReader.initProperties();
    private static final String url = property.getProperty("LoginPageUrl");
    private static final By LOGIN_CONTAINER_LOCATOR = By.xpath("//form[@class=\"form js-login-form\"]");
    private static final By LOGIN_FORM_LOCATOR = By.name("st.email");
    private static final By PASSWORD_FORM_LOCATOR = By.name("st.password");
    private static final By SIGN_IN_BTN_LOCATOR = By.xpath("//input[contains(@class, \"button-pro\")]");
    private static final By SIGN_IN_WITH_QR_BTN_LOCATOR = By.xpath("//a[@aria-label=\"Войти по QR-коду\"]");
    private static final By CAN_NOT_SIGN_IN_BTN_LOCATOR = By.xpath("//div[@class=\"recovery-link\"]");
    private static final By SIGN_UP_LOCATOR = By.xpath("//a[@class=\"button-pro __sec mb-3x\"]");
    private static final By INCORRECT_LOGIN_LOCATOR = By.xpath("//div[text()=\"Неправильно указан логин и/или пароль\"]");
    private static final By LOGIN_LABEL_LOCATOR = By.xpath("//label[@for=\"field_email\"]");
    private static final By PASSWORD_LABEL_LOCATOR = By.xpath("//label[@for=\"field_password\"]");
    private static final By PROFILES_LIST_TAB_LOCATOR = By.xpath("//a[text()=\"Список профилей\"]");
    private static final By ALL_LOGGED_PROFILES_NAMES_LOCATOR = By.xpath("//div[@class=\"user-profile-redesign_user-name\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() throws PageHasNotLoadedException {
        driver.get(url);
        checkIfPageLoaded();
        return this;
    }

    @Override
    public void checkIfPageLoaded() throws PageHasNotLoadedException {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            LOGIN_CONTAINER_LOCATOR
                    ));
        } catch (TimeoutException ex) {
            throw new PageHasNotLoadedException("Login page has no login container!");
        }
    }

    public LoginPage sendLogin(String login) {
        WebElement loginForm = driver.findElement(LOGIN_FORM_LOCATOR);
        if (loginForm.isDisplayed()) {
            loginForm.sendKeys(login);
        }
        return this;
    }

    public LoginPage sendPassword(String password) {
        WebElement passwordForm = driver.findElement(PASSWORD_FORM_LOCATOR);
        if (passwordForm.isDisplayed()) {
            passwordForm.sendKeys(password);
        }
        return this;
    }

    public UserPage signIn() {
        WebElement signInButton = driver.findElement(SIGN_IN_BTN_LOCATOR);
        if (signInButton.isDisplayed()) {
            signInButton.click();
        }
        try {
            driver.findElement(INCORRECT_LOGIN_LOCATOR);
        } catch (NoSuchElementException exception) {
            return new UserPage(driver).getUserPage();
        }
        return null;
    }

    public AccessRecoveryPage restoreAccess() {
        WebElement canNotSignInBtn = driver.findElement(CAN_NOT_SIGN_IN_BTN_LOCATOR);
        canNotSignInBtn.click();
        return new AccessRecoveryPage();
    }

    public LoginPage signInWithQrCode() {
        WebElement signInWithQrCodeBtn = driver.findElement(SIGN_IN_WITH_QR_BTN_LOCATOR);
        signInWithQrCodeBtn.click();
        return this;
    }

    public RegisterPage signUp() {
        WebElement signUpBtn = driver.findElement(SIGN_UP_LOCATOR);
        if (signUpBtn.isDisplayed()) {
            signUpBtn.click();
        }
        return new RegisterPage();
    }

    public MailPage goToMail() {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        MailPage mail = null;
        if (toolbar.isDisplayed()) {
            mail = toolbar.goToMail();
        }
        return mail;
    }

    public String getLoginLabelText() {
        WebElement loginLabel = driver.findElement(LOGIN_LABEL_LOCATOR);
        return loginLabel.getText();
    }

    public String getPasswordLabelText() {
        WebElement passwordLabel = driver.findElement(PASSWORD_LABEL_LOCATOR);
        return passwordLabel.getText();
    }

    public LoginPage changeLanguage(String language) {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        return toolbar.changeLanguageTo(language) ? this : null;
    }

    public HashSet<String> getLoggedProfilesNames() {
        WebElement profilesListTab = driver.findElement(PROFILES_LIST_TAB_LOCATOR);
        if (profilesListTab.isDisplayed()) {
            profilesListTab.click();
            List<WebElement> profilesNamesElements = driver.findElements(ALL_LOGGED_PROFILES_NAMES_LOCATOR);
            if (!profilesNamesElements.isEmpty()) {
                HashSet<String> profilesNames = new HashSet<>();
                for (WebElement prNameEl : profilesNamesElements) {
                    profilesNames.add(prNameEl.getText());
                }
                return profilesNames;
            }
        }
        return new HashSet<>();
    }
}

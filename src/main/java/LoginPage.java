import Utils.LoggedProfileWrapper;
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
        assert loginForm.isDisplayed() : "Login form is not visible";
        loginForm.sendKeys(login);
        return this;
    }

    public LoginPage sendPassword(String password) {
        WebElement passwordForm = driver.findElement(PASSWORD_FORM_LOCATOR);
        assert passwordForm.isDisplayed() : "Password form is not visible";
        passwordForm.sendKeys(password);
        return this;
    }

    public UserPage signIn() throws PageHasNotLoadedException {
        WebElement signInButton = driver.findElement(SIGN_IN_BTN_LOCATOR);
        assert signInButton.isDisplayed() : "Sign in button is not visible";
        signInButton.click();
        try {
            driver.findElement(INCORRECT_LOGIN_LOCATOR);
        } catch (NoSuchElementException exception) {
            return new UserPage(driver).getUserPage();
        }
        return null;
    }

    public AccessRecoveryPage restoreAccess() {
        WebElement canNotSignInBtn = driver.findElement(CAN_NOT_SIGN_IN_BTN_LOCATOR);
        assert canNotSignInBtn.isDisplayed() : "Can't sign in button is not visible";
        canNotSignInBtn.click();
        return new AccessRecoveryPage();
    }

    public LoginPage signInWithQrCode() {
        WebElement signInWithQrCodeBtn = driver.findElement(SIGN_IN_WITH_QR_BTN_LOCATOR);
        assert signInWithQrCodeBtn.isDisplayed() : "Sign in with QR code button is not visible";
        signInWithQrCodeBtn.click();
        return this;
    }

    public RegisterPage signUp() {
        WebElement signUpBtn = driver.findElement(SIGN_UP_LOCATOR);
        assert signUpBtn.isDisplayed() : "Sign up button is not visible";
        signUpBtn.click();
        return new RegisterPage();
    }

    public MailPage goToMail() {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        assert toolbar.isDisplayed() : "Toolbar is not visible";
        return toolbar.goToMail();
    }

    public String getLoginLabelText() {
        WebElement loginLabel = driver.findElement(LOGIN_LABEL_LOCATOR);
        assert loginLabel.isDisplayed() : "Login label is not visible";
        return loginLabel.getText();
    }

    public String getPasswordLabelText() {
        WebElement passwordLabel = driver.findElement(PASSWORD_LABEL_LOCATOR);
        assert passwordLabel.isDisplayed() : "Password label is not visible";
        return passwordLabel.getText();
    }

    public LoginPage changeLanguage(String language) {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        assert toolbar.isDisplayed() : "Toolbar is not visible";
        return toolbar.changeLanguageTo(language) ? this : null;
    }

    public HashSet<String> getLoggedProfilesNames() {
        LoginPage loggedProfiles = openProfiles();
        List<WebElement> profilesNamesElements = loggedProfiles.driver.findElements(ALL_LOGGED_PROFILES_NAMES_LOCATOR);
        if (!profilesNamesElements.isEmpty()) {
            HashSet<String> profilesNames = new HashSet<>();
            for (WebElement prNameEl : profilesNamesElements) {
                profilesNames.add(prNameEl.getText());
            }
            return profilesNames;
        }
        return new HashSet<>();
    }

    public List<String> getNames() {
        openProfiles();
        WebElement profilesList = driver.findElement(PROFILES_LIST_TAB_LOCATOR);
        LoggedProfileWrapper wrapper = new LoggedProfileWrapper(driver, profilesList);
        return wrapper.getLoggedProfilesNames();
    }

    public LoginPage openProfiles() {
        WebElement profilesListTab = driver.findElement(PROFILES_LIST_TAB_LOCATOR);
        assert profilesListTab.isDisplayed() : "Profiles tab is not displayed";
        profilesListTab.click();
        return this;
    }
}

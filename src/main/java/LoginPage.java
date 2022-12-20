import Utils.LoggedProfileWrapper;
import Utils.PropertiesReader;
import Utils.User;
import org.openqa.selenium.*;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;

public class LoginPage extends BasePage {

    private static final Properties property = PropertiesReader.initProperties();
    private static final String url = property.getProperty("LoginPageUrl");
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

    public LoginPage open() {
        driver.get(url);
        checkIfPageLoaded();
        return this;
    }

    @Override
    public void checkIfPageLoaded() {
        driverWrapper.waitUntilVisible(LOGIN_FORM_LOCATOR, 1);
        driverWrapper.waitUntilVisible(PASSWORD_FORM_LOCATOR, 1);
        driverWrapper.waitUntilClickable(SIGN_IN_BTN_LOCATOR, 1);
    }

    public LoginPage sendLogin(String login) {
        driverWrapper.waitUntilVisible(LOGIN_FORM_LOCATOR, 1);
        WebElement loginForm = driver.findElement(LOGIN_FORM_LOCATOR);
        loginForm.sendKeys(login);
        return this;
    }

    public LoginPage sendPassword(String password) {
        driverWrapper.waitUntilVisible(PASSWORD_FORM_LOCATOR, 1);
        WebElement passwordForm = driver.findElement(PASSWORD_FORM_LOCATOR);
        passwordForm.sendKeys(password);
        return this;
    }

    public UserPage signIn() {
        driverWrapper.waitUntilClickable(SIGN_IN_BTN_LOCATOR, 1);
        WebElement signInButton = driver.findElement(SIGN_IN_BTN_LOCATOR);
        signInButton.click();
        try {
            driver.findElement(INCORRECT_LOGIN_LOCATOR);
        } catch (NoSuchElementException exception) {
            return new UserPage(driver).getUserPage();
        }
        return null;
    }

    public UserPage login(User user) {
        sendLogin(user.getLogin());
        sendPassword(user.getPassword());
        return signIn();
    }

    public AccessRecoveryPage restoreAccess() {
        driverWrapper.waitUntilClickable(CAN_NOT_SIGN_IN_BTN_LOCATOR, 1);
        WebElement canNotSignInBtn = driver.findElement(CAN_NOT_SIGN_IN_BTN_LOCATOR);
        canNotSignInBtn.click();
        return new AccessRecoveryPage();
    }

    public LoginPage signInWithQrCode() {
        driverWrapper.waitUntilClickable(SIGN_IN_WITH_QR_BTN_LOCATOR, 1);
        WebElement signInWithQrCodeBtn = driver.findElement(SIGN_IN_WITH_QR_BTN_LOCATOR);
        signInWithQrCodeBtn.click();
        return this;
    }

    public RegisterPage signUp() {
        driverWrapper.waitUntilClickable(SIGN_UP_LOCATOR, 1);
        WebElement signUpBtn = driver.findElement(SIGN_UP_LOCATOR);
        signUpBtn.click();
        return new RegisterPage();
    }

    public MailPage goToMail() {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        toolbar.checkIfDisplayed();
        return toolbar.goToMail();
    }

    public String getLoginLabelText() {
        driverWrapper.waitUntilVisible(LOGIN_LABEL_LOCATOR, 1);
        WebElement loginLabel = driver.findElement(LOGIN_LABEL_LOCATOR);
        return loginLabel.getText();
    }

    public String getPasswordLabelText() {
        driverWrapper.waitUntilVisible(PASSWORD_FORM_LOCATOR, 1);
        WebElement passwordLabel = driver.findElement(PASSWORD_LABEL_LOCATOR);
        return passwordLabel.getText();
    }

    public LoginPage changeLanguage(String language) {
        LoginPageToolbar toolbar = new LoginPageToolbar(driver);
        toolbar.checkIfDisplayed();
        toolbar.changeLanguageTo(language);
        return this;
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
        driverWrapper.waitUntilClickable(PROFILES_LIST_TAB_LOCATOR, 1);
        WebElement profilesListTab = driver.findElement(PROFILES_LIST_TAB_LOCATOR);
        profilesListTab.click();
        return this;
    }
}
